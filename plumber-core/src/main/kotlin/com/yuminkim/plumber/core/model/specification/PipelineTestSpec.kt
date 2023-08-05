package com.yuminkim.plumber.core.model.specification

data class PipelineTestSpec(
  val name: String,
  val description: String,
  val trigger: TriggerPipelineSpec,
  val stages: List<StageTestSpec>,
  val expectedStatus: PipelineTerminalStatus,
  val timeout: Timeout = Timeout.of(20, Timeout.Unit.MINUTES)
)

enum class PipelineTerminalStatus {
  SUCCEEDED,
  FAILED,
  ABORTED
}
