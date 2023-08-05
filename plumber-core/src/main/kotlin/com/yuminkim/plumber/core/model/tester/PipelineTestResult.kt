package com.yuminkim.plumber.core.model.tester

class PipelineTestResult(
  val overallStatus: TestResultStatus,
  val detail: PipelineTestResultDetail
)

class PipelineTestResultDetail(
  val stages: List<StageTestResult>,
  val status: TestResultStatus
)

class StageTestResult(
  val name: String,
  val status: TestResultStatus
)
