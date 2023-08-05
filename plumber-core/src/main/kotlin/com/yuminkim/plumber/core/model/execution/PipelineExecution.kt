package com.yuminkim.plumber.core.model.execution

import com.yuminkim.plumber.core.model.pipeline.PipelineStatus
import com.yuminkim.plumber.core.model.pipeline.StageStatus
import java.time.ZonedDateTime

class PipelineExecution(
  val stages: List<StageExecution>,
  val status: PipelineStatus,
  val startedAt: ZonedDateTime,
)

class StageExecution(
  val name: String,
  val status: StageStatus,
)
