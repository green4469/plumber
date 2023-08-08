package com.yuminkim.plumber.core.model.tester

import com.yuminkim.plumber.core.model.tester.status.TestResultStatus

class PipelineTestResult(
  val overallStatus: TestResultStatus,
  val detail: PipelineTestResultDetail
) {
  companion object {
    fun passed(detail: PipelineTestResultDetail): PipelineTestResult {
      return PipelineTestResult(
        overallStatus = TestResultStatus.PASSED,
        detail = detail
      )
    }

    fun failed(detail: PipelineTestResultDetail, reason: String): PipelineTestResult {
      return PipelineTestResult(
        overallStatus = TestResultStatus.FAILED(reason),
        detail = detail
      )
    }

    fun testing(detail: PipelineTestResultDetail): PipelineTestResult {
      return PipelineTestResult(
        overallStatus = TestResultStatus.TESTING,
        detail = detail
      )
    }
  }
}

class PipelineTestResultDetail(
  val stages: List<StageTestResult>,
  val status: TestResultStatus
)

class StageTestResult(
  val name: String,
  val status: TestResultStatus
) {
  companion object {
    fun failed(name: String, reason: String): StageTestResult {
      return StageTestResult(
        name = name,
        status = TestResultStatus.FAILED(reason)
      )
    }
  }
}
