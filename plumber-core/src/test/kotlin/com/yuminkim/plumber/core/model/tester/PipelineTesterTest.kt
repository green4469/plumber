package com.yuminkim.plumber.core.model.tester

import com.yuminkim.plumber.core.model.execution.PipelineExecution
import com.yuminkim.plumber.core.model.execution.PipelineExecutionFixtures
import com.yuminkim.plumber.core.model.specification.PipelineTestSpec
import com.yuminkim.plumber.core.model.tester.status.TestResultStatus
import io.kotest.assertions.asClue
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.every
import io.mockk.mockk
import java.time.ZonedDateTime

class PipelineTesterTest : DescribeSpec({
  lateinit var tester: PipelineTester

  describe("test") {
    lateinit var timeHolder: TimeHolder
    lateinit var spec: PipelineTestSpec
    lateinit var execution: PipelineExecution

    context("when status test results are all passed") {
      beforeEach {
        spec = PipelineTestSpecFixtures.ExpectingSucceeded.create()
        execution = PipelineExecutionFixtures.Succeeded.create()
        timeHolder = mockk<TimeHolder> {
          every { now() } returns ZonedDateTime.parse("2023-01-01T00:19:59Z")
        }
        tester = PipelineTester(timeHolder)
      }

      it("should return pipeline test result 'PASSED'") {
        val result = tester.test(spec, execution)

        result.overallStatus.shouldBeInstanceOf<TestResultStatus.PASSED>()

        result.detail.asClue {
          it.status.shouldBeInstanceOf<TestResultStatus.PASSED>()

          it.stages.size shouldBe 2
          it.stages[0].name shouldBe "Build application"
          it.stages[0].status.shouldBeInstanceOf<TestResultStatus.PASSED>()
          it.stages[1].name shouldBe "Deploy"
          it.stages[1].status.shouldBeInstanceOf<TestResultStatus.PASSED>()
        }
      }
    }

    context("when any status test result is failed") {
      beforeEach {
        spec = PipelineTestSpecFixtures.ExpectingSucceeded.create()
        execution = PipelineExecutionFixtures.Failed.create()
        timeHolder = mockk<TimeHolder> {
          every { now() } returns ZonedDateTime.parse("2023-01-01T00:19:59Z")
        }
        tester = PipelineTester(timeHolder)
      }

      it("should return pipeline test result 'FAILED'") {
        val result = tester.test(spec, execution)

        result.overallStatus.shouldBeInstanceOf<TestResultStatus.FAILED>()

        result.detail.asClue {
          it.status.shouldBeInstanceOf<TestResultStatus.FAILED>()

          it.stages.size shouldBe 2
          it.stages[0].name shouldBe "Build application"
          it.stages[0].status.shouldBeInstanceOf<TestResultStatus.PASSED>()
          it.stages[1].name shouldBe "Deploy"
          it.stages[1].status.shouldBeInstanceOf<TestResultStatus.FAILED>()
        }
      }
    }

    context("when any status test result is testing and no failed") {
      beforeEach {
        spec = PipelineTestSpecFixtures.ExpectingSucceeded.create()
        execution = PipelineExecutionFixtures.Running.create()
        timeHolder = mockk<TimeHolder> {
          every { now() } returns ZonedDateTime.parse("2023-01-01T00:19:59Z")
        }
        tester = PipelineTester(timeHolder)
      }

      it("should return pipeline test result 'TESTING'") {
        val result = tester.test(spec, execution)

        result.overallStatus.shouldBeInstanceOf<TestResultStatus.TESTING>()

        result.detail.asClue {
          it.status.shouldBeInstanceOf<TestResultStatus.TESTING>()

          it.stages.size shouldBe 2
          it.stages[0].name shouldBe "Build application"
          it.stages[0].status.shouldBeInstanceOf<TestResultStatus.PASSED>()
          it.stages[1].name shouldBe "Deploy"
          it.stages[1].status.shouldBeInstanceOf<TestResultStatus.TESTING>()
        }
      }
    }

    context("when any status test is testing but pipeline test timed out") {
      beforeEach {
        spec = PipelineTestSpecFixtures.ExpectingSucceeded.create()
        execution = PipelineExecutionFixtures.Running.create()
        timeHolder = mockk<TimeHolder> {
          every { now() } returns ZonedDateTime.parse("2023-01-01T00:20:01Z")
        }
        tester = PipelineTester(timeHolder)
      }

      it("should return pipeline test result 'FAILED'") {
        val result = tester.test(spec, execution)

        result.overallStatus.shouldBeInstanceOf<TestResultStatus.FAILED>()

        result.detail.asClue {
          it.status.shouldBeInstanceOf<TestResultStatus.TESTING>()

          it.stages.size shouldBe 2
          it.stages[0].name shouldBe "Build application"
          it.stages[0].status.shouldBeInstanceOf<TestResultStatus.PASSED>()
          it.stages[1].name shouldBe "Deploy"
          it.stages[1].status.shouldBeInstanceOf<TestResultStatus.TESTING>()
        }
      }
    }

    context("when stage in spec is not found in execution") {
      beforeEach {
        spec = PipelineTestSpecFixtures.ExpectingSucceeded.create()
        execution = PipelineExecutionFixtures.StageMissing.create()
        timeHolder = mockk<TimeHolder> {
          every { now() } returns ZonedDateTime.parse("2023-01-01T00:19:59Z")
        }
        tester = PipelineTester(timeHolder)
      }

      it("should return pipeline test result 'FAILED'") {
        val result = tester.test(spec, execution)

        result.overallStatus.shouldBeInstanceOf<TestResultStatus.FAILED>()

        result.detail.asClue {
          it.status.shouldBeInstanceOf<TestResultStatus.PASSED>()

          it.stages.size shouldBe 2
          it.stages[0].name shouldBe "Build application"
          it.stages[0].status.shouldBeInstanceOf<TestResultStatus.PASSED>()
          it.stages[1].name shouldBe "Deploy"
          it.stages[1].status.shouldBeInstanceOf<TestResultStatus.FAILED>()
        }
      }
    }
  }
})
