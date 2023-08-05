package com.yuminkim.plumber.core.model.tester

enum class PipelineTestStatus {
  TESTING,

  // Terminal status
  PASSED,
  FAILED,
  TIMED_OUT;
}
