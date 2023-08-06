package com.yuminkim.plumber.core.model.tester.status

import com.yuminkim.plumber.core.model.execution.StageExecutionStatus
import com.yuminkim.plumber.core.model.specification.ExpectedStageTerminalStatus

object StageStatusTester {
  fun test(expected: ExpectedStageTerminalStatus, actual: StageExecutionStatus): TestResultStatus {
    if (actual == StageExecutionStatus.WAITING || actual == StageExecutionStatus.RUNNING) {
      return TestResultStatus.TESTING
    }

    if (actual.name == expected.name) {
      return TestResultStatus.PASSED
    }

    return TestResultStatus.FAILED(
      "Stage execution status($actual) does not match with expected status($expected)."
    )
  }
}
