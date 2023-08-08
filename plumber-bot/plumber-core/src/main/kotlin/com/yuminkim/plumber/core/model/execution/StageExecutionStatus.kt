package com.yuminkim.plumber.core.model.execution

enum class StageExecutionStatus {
  WAITING,
  RUNNING,

  // Terminal status
  SKIPPED,
  SUCCEEDED,
  FAILED,
  ABORTED;

  fun isTerminal(): Boolean {
    return this == SKIPPED || this == SUCCEEDED || this == FAILED || this == ABORTED
  }
}
