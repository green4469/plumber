package com.yuminkim.plumber.core.model

import com.yuminkim.plumber.core.model.executor.PipelineExecutor
import com.yuminkim.plumber.core.model.specification.PipelineTestSpec
import com.yuminkim.plumber.core.model.tester.PipelineTestResult
import com.yuminkim.plumber.core.model.tester.PipelineTester
import org.springframework.stereotype.Component

@Component
class PipelineTestEngine(
  private val pipelineExecutor: PipelineExecutor,
  private val pipelineTester: PipelineTester
) {
  fun run(spec: PipelineTestSpec): PipelineTestResult {
    val pipelineExecutionReference = pipelineExecutor.trigger(spec.trigger)

    while (true) {
      val execution = pipelineExecutor.get(pipelineExecutionReference)
      val testResult = pipelineTester.test(spec, execution)
      if (testResult.overallStatus.isTerminal()) {
        return testResult
      }
      // TODO: Do pipeline interaction if any stage execution status is PAUSED_WAITING_USER_INPUT
      Thread.sleep(POLLING_INTERVAL)
    }
  }

  companion object {
    private const val POLLING_INTERVAL = 5000L
  }
}
