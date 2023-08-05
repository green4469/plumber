package com.yuminkim.plumber.core.model.tester

class PipelineTestResult(
  val status: PipelineTestResultStatus,
  val reason: String?, // Only available when status is FAILED
  val detail: PipelineTestResultDetail,
)

enum class PipelineTestResultStatus{
  TESTING,

  // Terminal status
  PASSED,
  FAILED,
}

class PipelineTestResultDetail(
  val stages: List<StageTestResult>,
  val status: PipelineTestStatus
)

class StageTestResult(
  val name: String,
  val status: StageTestStatus,
)
