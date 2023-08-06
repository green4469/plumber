package com.yuminkim.plumber.core.model.tester.status

sealed class TestResultStatus {
  object TESTING : TestResultStatus()

  // Terminal status
  object PASSED : TestResultStatus()

  data class FAILED(
    val reason: String
  ) : TestResultStatus()

  fun isPassed(): Boolean {
    return this == PASSED
  }

  fun isFailed(): Boolean {
    return this is FAILED
  }
}
