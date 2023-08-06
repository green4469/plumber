package com.yuminkim.plumber.core.model.tester

import com.yuminkim.plumber.core.model.execution.PipelineExecution
import com.yuminkim.plumber.core.model.execution.StageExecution
import com.yuminkim.plumber.core.model.specification.PipelineTestSpec
import com.yuminkim.plumber.core.model.specification.StageTestSpec
import com.yuminkim.plumber.core.model.specification.Timeout
import com.yuminkim.plumber.core.model.tester.status.PipelineStatusTester
import com.yuminkim.plumber.core.model.tester.status.StageStatusTester
import com.yuminkim.plumber.core.model.tester.status.TestResultStatus
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class PipelineTester(
  private val timeHolder: TimeHolder
) {
  fun test(spec: PipelineTestSpec, execution: PipelineExecution): PipelineTestResult {
    val pipelineTestResultStatus = PipelineStatusTester.test(spec.expectedStatus, execution.status)
    val stageTestResults = testStages(spec.stages, execution.stages)
    return determineOverallTestResult(
      pipelineTestResultStatus = pipelineTestResultStatus,
      stageTestResults = stageTestResults,
      timeout = spec.timeout,
      startedAt = execution.startedAt
    )
  }

  private fun testStages(
    specStages: List<StageTestSpec>,
    executionStages: List<StageExecution>
  ): List<StageTestResult> {
    return specStages.map { stageTestSpec ->
      val stageExecution = executionStages.find { it.name == stageTestSpec.name }
        ?: return@map StageTestResult(
          name = stageTestSpec.name,
          status = TestResultStatus.FAILED("Stage execution(${stageTestSpec.name}) does not exist.")
        )

      val stageTestResultStatus =
        StageStatusTester.test(stageTestSpec.expectedStatus, stageExecution.status)

      StageTestResult(
        name = stageTestSpec.name,
        status = stageTestResultStatus
      )
    }
  }

  private fun determineOverallTestResult(
    pipelineTestResultStatus: TestResultStatus,
    stageTestResults: List<StageTestResult>,
    timeout: Timeout,
    startedAt: ZonedDateTime
  ): PipelineTestResult {
    val executionDuration = timeHolder.now().toEpochSecond() - startedAt.toEpochSecond()
    if (executionDuration > timeout.toSeconds()) {
      return PipelineTestResult(
        overallStatus = TestResultStatus.FAILED("Pipeline execution timed out. (timeout: $timeout)"),
        detail = PipelineTestResultDetail(
          stages = stageTestResults,
          status = pipelineTestResultStatus
        )
      )
    }

    if (pipelineTestResultStatus.isPassed() && stageTestResults.all { it.status.isPassed() }) {
      return PipelineTestResult(
        overallStatus = TestResultStatus.PASSED,
        detail = PipelineTestResultDetail(
          stages = stageTestResults,
          status = pipelineTestResultStatus
        )
      )
    }

    if (pipelineTestResultStatus.isFailed() || stageTestResults.any { it.status.isFailed() }) {
      return PipelineTestResult(
        overallStatus = TestResultStatus.FAILED("One or more tests failed."),
        detail = PipelineTestResultDetail(
          stages = stageTestResults,
          status = pipelineTestResultStatus
        )
      )
    }

    if (pipelineTestResultStatus == TestResultStatus.TESTING || stageTestResults.any { it.status == TestResultStatus.TESTING }) {
      return PipelineTestResult(
        overallStatus = TestResultStatus.TESTING,
        detail = PipelineTestResultDetail(
          stages = stageTestResults,
          status = pipelineTestResultStatus
        )
      )
    }

    throw IllegalStateException("Unexpected test result status.")
  }
}
