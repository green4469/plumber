package com.yuminkim.plumber.core.model.pipeline

enum class PipelineStatus {
  WAITING,
  RUNNING,

  // Terminal status
  SUCCEEDED,
  FAILED,
  ABORTED;
}
