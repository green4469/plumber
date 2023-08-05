package com.yuminkim.plumber.core.model.tester

class PipelineTestResult(
  val status: PipelineTestStatus,
  val reason: String,
  val detail: PipelineTestResultDetail,
)

class PipelineTestResultDetail(
  val stages: List<StageTestResult>,
  val status: PipelineTestStatus
)

class StageTestResult(
  val name: String,
  val status: StageTestStatus,
)
