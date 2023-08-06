package com.yuminkim.plumber.core.model.tester.status

import com.yuminkim.plumber.core.model.execution.PipelineExecutionStatus
import com.yuminkim.plumber.core.model.specification.ExpectedPipelineTerminalStatus
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.should
import io.kotest.matchers.types.beInstanceOf

class PipelineStatusTesterTest : DescribeSpec({
  describe("test") {
    context("when expected is SUCCEEDED") {
      listOf(
        PipelineStatusTestData(
          expected = ExpectedPipelineTerminalStatus.SUCCEEDED,
          actual = PipelineExecutionStatus.WAITING,
          shouldBe = TestResultStatus.TESTING
        ),
        PipelineStatusTestData(
          expected = ExpectedPipelineTerminalStatus.SUCCEEDED,
          actual = PipelineExecutionStatus.RUNNING,
          shouldBe = TestResultStatus.TESTING
        ),
        PipelineStatusTestData(
          expected = ExpectedPipelineTerminalStatus.SUCCEEDED,
          actual = PipelineExecutionStatus.SUCCEEDED,
          shouldBe = TestResultStatus.PASSED
        ),
        PipelineStatusTestData(
          expected = ExpectedPipelineTerminalStatus.SUCCEEDED,
          actual = PipelineExecutionStatus.FAILED,
          shouldBe = TestResultStatus.FAILED("")
        ),
        PipelineStatusTestData(
          expected = ExpectedPipelineTerminalStatus.SUCCEEDED,
          actual = PipelineExecutionStatus.ABORTED,
          shouldBe = TestResultStatus.FAILED("")
        ),
      ).forEach { (expected, actual, shouldBe) ->
        context("when expected is $expected and actual is $actual") {
          it("should be $shouldBe") {
            PipelineStatusTester.test(expected, actual) should beInstanceOf(shouldBe::class)
          }
        }
      }
    }

    context("when expected is FAILED") {
      listOf(
        PipelineStatusTestData(
          expected = ExpectedPipelineTerminalStatus.FAILED,
          actual = PipelineExecutionStatus.WAITING,
          shouldBe = TestResultStatus.TESTING
        ),
        PipelineStatusTestData(
          expected = ExpectedPipelineTerminalStatus.FAILED,
          actual = PipelineExecutionStatus.RUNNING,
          shouldBe = TestResultStatus.TESTING
        ),
        PipelineStatusTestData(
          expected = ExpectedPipelineTerminalStatus.FAILED,
          actual = PipelineExecutionStatus.SUCCEEDED,
          shouldBe = TestResultStatus.FAILED("")
        ),
        PipelineStatusTestData(
          expected = ExpectedPipelineTerminalStatus.FAILED,
          actual = PipelineExecutionStatus.FAILED,
          shouldBe = TestResultStatus.PASSED
        ),
        PipelineStatusTestData(
          expected = ExpectedPipelineTerminalStatus.FAILED,
          actual = PipelineExecutionStatus.ABORTED,
          shouldBe = TestResultStatus.FAILED("")
        ),
      ).forEach { (expected, actual, shouldBe) ->
        context("when expected is $expected and actual is $actual") {
          it("should be $shouldBe") {
            PipelineStatusTester.test(expected, actual) should beInstanceOf(shouldBe::class)
          }
        }
      }
    }

    context("when expected is ABORTED") {
      listOf(
        PipelineStatusTestData(
          expected = ExpectedPipelineTerminalStatus.ABORTED,
          actual = PipelineExecutionStatus.WAITING,
          shouldBe = TestResultStatus.TESTING
        ),
        PipelineStatusTestData(
          expected = ExpectedPipelineTerminalStatus.ABORTED,
          actual = PipelineExecutionStatus.RUNNING,
          shouldBe = TestResultStatus.TESTING
        ),
        PipelineStatusTestData(
          expected = ExpectedPipelineTerminalStatus.ABORTED,
          actual = PipelineExecutionStatus.SUCCEEDED,
          shouldBe = TestResultStatus.FAILED("")
        ),
        PipelineStatusTestData(
          expected = ExpectedPipelineTerminalStatus.ABORTED,
          actual = PipelineExecutionStatus.FAILED,
          shouldBe = TestResultStatus.FAILED("")
        ),
        PipelineStatusTestData(
          expected = ExpectedPipelineTerminalStatus.ABORTED,
          actual = PipelineExecutionStatus.ABORTED,
          shouldBe = TestResultStatus.PASSED
        ),
      ).forEach { (expected, actual, shouldBe) ->
        context("when expected is $expected and actual is $actual") {
          it("should be $shouldBe") {
            PipelineStatusTester.test(expected, actual) should beInstanceOf(shouldBe::class)
          }
        }
      }
    }
  }
})

data class PipelineStatusTestData(
  val expected: ExpectedPipelineTerminalStatus,
  val actual: PipelineExecutionStatus,
  val shouldBe: TestResultStatus
)
