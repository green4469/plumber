package com.yuminkim.plumber.core.model.pipeline

enum class StageStatus {
  WAITING,
  RUNNING,

  // Terminal status
  SKIPPED,
  SUCCEEDED,
  FAILED,
  ABORTED;
}
