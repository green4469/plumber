package com.yuminkim.plumber.core.model.tester

enum class StageTestStatus {
  TESTING,

  // Terminal status
  PASSED,
  FAILED, // Expected status is not matched
  MISSING; // Expected stage is not found
}
