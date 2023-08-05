package com.yuminkim.plumber.core.model.execution

import java.time.ZonedDateTime

class PipelineExecution(
  val stages: List<StageExecution>,
  val status: PipelineExecutionStatus,
  val startedAt: ZonedDateTime
)
