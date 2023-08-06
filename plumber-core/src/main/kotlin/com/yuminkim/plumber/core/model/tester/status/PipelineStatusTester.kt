package com.yuminkim.plumber.core.model.tester.status

import com.yuminkim.plumber.core.model.execution.PipelineExecutionStatus
import com.yuminkim.plumber.core.model.specification.ExpectedPipelineTerminalStatus

object PipelineStatusTester {
  fun test(expected: ExpectedPipelineTerminalStatus, actual: PipelineExecutionStatus): TestResultStatus {
    if (actual.isTerminal().not()) {
      return TestResultStatus.TESTING
    }

    if (actual.matches(expected)) {
      return TestResultStatus.PASSED
    }

    return TestResultStatus.FAILED(
      "Pipeline execution status($actual) does not match with expected status($expected)."
    )
  }

  private fun PipelineExecutionStatus.matches(expected: ExpectedPipelineTerminalStatus): Boolean {
    return this.name == expected.name
  }
}
