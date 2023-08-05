package com.yuminkim.plumber.core.model.execution

enum class PipelineExecutionStatus {
  WAITING,
  RUNNING,

  // Terminal status
  SUCCEEDED,
  FAILED,
  ABORTED;
}
