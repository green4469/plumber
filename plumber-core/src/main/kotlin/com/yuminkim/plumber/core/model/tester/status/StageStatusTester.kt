package com.yuminkim.plumber.core.model.tester.status

import com.yuminkim.plumber.core.model.execution.StageExecutionStatus
import com.yuminkim.plumber.core.model.specification.ExpectedStageTerminalStatus

object StageStatusTester {
  fun test(expected: ExpectedStageTerminalStatus, actual: StageExecutionStatus): TestResultStatus {
    if (actual.isTerminal().not()) {
      return TestResultStatus.TESTING
    }

    if (actual.matches(expected)) {
      return TestResultStatus.PASSED
    }

    return TestResultStatus.FAILED(
      "Stage execution status($actual) does not match with expected status($expected)."
    )
  }

  private fun StageExecutionStatus.matches(expected: ExpectedStageTerminalStatus): Boolean {
    return this.name == expected.name
  }
}
