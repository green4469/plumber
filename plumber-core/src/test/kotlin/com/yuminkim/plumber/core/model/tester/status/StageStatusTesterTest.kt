package com.yuminkim.plumber.core.model.tester.status

import com.yuminkim.plumber.core.model.execution.StageExecutionStatus
import com.yuminkim.plumber.core.model.specification.ExpectedStageTerminalStatus
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.should
import io.kotest.matchers.types.beInstanceOf

class StageStatusTesterTest : DescribeSpec({
  describe("test") {
    context("when expected is SUCCEEDED") {
      listOf(
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.SUCCEEDED,
          actual = StageExecutionStatus.WAITING,
          shouldBe = TestResultStatus.TESTING
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.SUCCEEDED,
          actual = StageExecutionStatus.RUNNING,
          shouldBe = TestResultStatus.TESTING
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.SUCCEEDED,
          actual = StageExecutionStatus.SKIPPED,
          shouldBe = TestResultStatus.FAILED("")
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.SUCCEEDED,
          actual = StageExecutionStatus.SUCCEEDED,
          shouldBe = TestResultStatus.PASSED
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.SUCCEEDED,
          actual = StageExecutionStatus.FAILED,
          shouldBe = TestResultStatus.FAILED("")
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.SUCCEEDED,
          actual = StageExecutionStatus.ABORTED,
          shouldBe = TestResultStatus.FAILED("")
        )
      ).forEach { (expected, actual, shouldBe) ->
        context("when expected is $expected and actual is $actual") {
          it("should be $shouldBe") {
            StageStatusTester.test(expected, actual) should beInstanceOf(shouldBe::class)
          }
        }
      }
    }

    context("when expected is FAILED") {
      listOf(
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.FAILED,
          actual = StageExecutionStatus.WAITING,
          shouldBe = TestResultStatus.TESTING
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.FAILED,
          actual = StageExecutionStatus.RUNNING,
          shouldBe = TestResultStatus.TESTING
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.FAILED,
          actual = StageExecutionStatus.SKIPPED,
          shouldBe = TestResultStatus.FAILED("")
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.FAILED,
          actual = StageExecutionStatus.SUCCEEDED,
          shouldBe = TestResultStatus.FAILED("")
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.FAILED,
          actual = StageExecutionStatus.FAILED,
          shouldBe = TestResultStatus.PASSED
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.FAILED,
          actual = StageExecutionStatus.ABORTED,
          shouldBe = TestResultStatus.FAILED("")
        )
      ).forEach { (expected, actual, shouldBe) ->
        context("when expected is $expected and actual is $actual") {
          it("should be $shouldBe") {
            StageStatusTester.test(expected, actual) should beInstanceOf(shouldBe::class)
          }
        }
      }
    }

    context("when expected is ABORTED") {
      listOf(
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.ABORTED,
          actual = StageExecutionStatus.WAITING,
          shouldBe = TestResultStatus.TESTING
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.ABORTED,
          actual = StageExecutionStatus.RUNNING,
          shouldBe = TestResultStatus.TESTING
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.ABORTED,
          actual = StageExecutionStatus.SKIPPED,
          shouldBe = TestResultStatus.FAILED("")
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.ABORTED,
          actual = StageExecutionStatus.SUCCEEDED,
          shouldBe = TestResultStatus.FAILED("")
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.ABORTED,
          actual = StageExecutionStatus.FAILED,
          shouldBe = TestResultStatus.FAILED("")
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.ABORTED,
          actual = StageExecutionStatus.ABORTED,
          shouldBe = TestResultStatus.PASSED
        )
      ).forEach { (expected, actual, shouldBe) ->
        context("when expected is $expected and actual is $actual") {
          it("should be $shouldBe") {
            StageStatusTester.test(expected, actual) should beInstanceOf(shouldBe::class)
          }
        }
      }
    }

    context("when expected is SKIPPED") {
      listOf(
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.SKIPPED,
          actual = StageExecutionStatus.WAITING,
          shouldBe = TestResultStatus.TESTING
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.SKIPPED,
          actual = StageExecutionStatus.RUNNING,
          shouldBe = TestResultStatus.TESTING
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.SKIPPED,
          actual = StageExecutionStatus.SKIPPED,
          shouldBe = TestResultStatus.PASSED
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.SKIPPED,
          actual = StageExecutionStatus.SUCCEEDED,
          shouldBe = TestResultStatus.FAILED("")
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.SKIPPED,
          actual = StageExecutionStatus.FAILED,
          shouldBe = TestResultStatus.FAILED("")
        ),
        StageStatusTestData(
          expected = ExpectedStageTerminalStatus.SKIPPED,
          actual = StageExecutionStatus.ABORTED,
          shouldBe = TestResultStatus.FAILED("")
        )
      ).forEach { (expected, actual, shouldBe) ->
        context("when expected is $expected and actual is $actual") {
          it("should be $shouldBe") {
            StageStatusTester.test(expected, actual) should beInstanceOf(shouldBe::class)
          }
        }
      }
    }
  }
})

data class StageStatusTestData(
  val expected: ExpectedStageTerminalStatus,
  val actual: StageExecutionStatus,
  val shouldBe: TestResultStatus
)
