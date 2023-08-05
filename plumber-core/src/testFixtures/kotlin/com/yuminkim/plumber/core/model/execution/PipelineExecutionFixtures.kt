package com.yuminkim.plumber.core.model.execution

import java.time.ZonedDateTime

object PipelineExecutionFixtures {
  object Failed {
    fun create(): PipelineExecution {
      return PipelineExecution(
        stages = listOf(
          StageExecution(
            // Stage test name has interdependency with pipeline test spec
            name = "Build application",
            status = StageExecutionStatus.FAILED
          ),
          StageExecution(
            name = "Deploy",
            status = StageExecutionStatus.WAITING
          )
        ),
        status = PipelineExecutionStatus.FAILED,
        startedAt = ZonedDateTime.parse("2023-01-01T00:00:00Z")
      )
    }
  }
}
