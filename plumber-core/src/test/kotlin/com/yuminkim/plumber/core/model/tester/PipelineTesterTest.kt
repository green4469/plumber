package com.yuminkim.plumber.core.model.tester

import com.yuminkim.plumber.core.model.execution.PipelineExecution
import com.yuminkim.plumber.core.model.execution.PipelineExecutionFixtures
import com.yuminkim.plumber.core.model.specification.PipelineTestSpec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PipelineTesterTest : DescribeSpec({
  describe("test") {
    lateinit var spec: PipelineTestSpec
    lateinit var execution: PipelineExecution

    context("when pipeline test spec and pipeline execution does not match") {
      beforeEach {
        spec = PipelineTestSpecFixtures.ExpectingSuccess.create()
        execution = PipelineExecutionFixtures.Failed.create()
      }

      it("should return pipeline test result 'FAILED'") {
        val result = PipelineTester.test(spec, execution)
        result.status shouldBe PipelineTestStatus.FAILED
      }
    }
  }
})
