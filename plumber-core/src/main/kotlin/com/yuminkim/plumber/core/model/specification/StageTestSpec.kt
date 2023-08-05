package com.yuminkim.plumber.core.model.specification

data class StageTestSpec(
  val name: String,
  val description: String,
  val expectedStatus: StageTerminalStatus
)

enum class StageTerminalStatus {
  SUCCEEDED,
  FAILED,
  ABORTED
}
