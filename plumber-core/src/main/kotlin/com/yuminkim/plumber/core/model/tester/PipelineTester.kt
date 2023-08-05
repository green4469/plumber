package com.yuminkim.plumber.core.model.tester

import com.yuminkim.plumber.core.model.execution.PipelineExecution
import com.yuminkim.plumber.core.model.execution.PipelineExecutionStatus
import com.yuminkim.plumber.core.model.execution.StageExecution
import com.yuminkim.plumber.core.model.execution.StageExecutionStatus
import com.yuminkim.plumber.core.model.specification.ExpectedPipelineTerminalStatus
import com.yuminkim.plumber.core.model.specification.ExpectedStageTerminalStatus
import com.yuminkim.plumber.core.model.specification.PipelineTestSpec
import com.yuminkim.plumber.core.model.specification.StageTestSpec

object PipelineTester {
  fun test(spec: PipelineTestSpec, execution: PipelineExecution): PipelineTestResult {
    val pipelineTestResultStatus = testPipelineStatus(spec.expectedStatus, execution.status)
    val stageTestResults = testStages(spec.stages, execution.stages)
    return determineOverallTestResult(pipelineTestResultStatus, stageTestResults)
  }

  private fun testPipelineStatus(
    expected: ExpectedPipelineTerminalStatus,
    actual: PipelineExecutionStatus
  ): TestResultStatus {
    if (actual == PipelineExecutionStatus.WAITING || actual == PipelineExecutionStatus.RUNNING) {
      return TestResultStatus.TESTING
    }

    if (actual.name == expected.name) {
      return TestResultStatus.PASSED
    }

    return TestResultStatus.FAILED("Pipeline execution status($actual) does not match with expected status($expected).")
  }

  private fun testStages(
    specStages: List<StageTestSpec>,
    executionStages: List<StageExecution>
  ): List<StageTestResult> = specStages.map { stageTestSpec ->
    val stageExecution = executionStages.find { it.name == stageTestSpec.name }
      ?: return@map StageTestResult(
        name = stageTestSpec.name,
        status = TestResultStatus.FAILED("Stage execution(${stageTestSpec.name}) does not exist.")
      )

    val stageTestResultStatus = testStageStatus(stageTestSpec.expectedStatus, stageExecution.status)

    StageTestResult(
      name = stageTestSpec.name,
      status = stageTestResultStatus
    )
  }

  private fun testStageStatus(
    expected: ExpectedStageTerminalStatus,
    actual: StageExecutionStatus
  ): TestResultStatus {
    if (actual == StageExecutionStatus.WAITING || actual == StageExecutionStatus.RUNNING) {
      return TestResultStatus.TESTING
    }

    if (actual.name == expected.name) {
      return TestResultStatus.PASSED
    }

    return TestResultStatus.FAILED("Stage execution status($actual) does not match with expected status($expected).")
  }

  private fun determineOverallTestResult(
    pipelineTestResultStatus: TestResultStatus,
    stageTestResults: List<StageTestResult>
  ): PipelineTestResult {
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
