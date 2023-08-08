package com.yuminkim.plumber.core.model.execution

enum class PipelineExecutionStatus {
  WAITING,
  RUNNING,

  // Terminal status
  SUCCEEDED,
  FAILED,
  ABORTED;

  fun isTerminal(): Boolean {
    return this == SUCCEEDED || this == FAILED || this == ABORTED
  }
}
