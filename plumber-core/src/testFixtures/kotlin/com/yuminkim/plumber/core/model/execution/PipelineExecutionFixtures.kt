package com.yuminkim.plumber.core.model.execution

import java.time.ZonedDateTime

object PipelineExecutionFixtures {
  object Succeeded {
    fun create(): PipelineExecution {
      return PipelineExecution(
        stages = listOf(
          StageExecution(
            name = "Build application",
            status = StageExecutionStatus.SUCCEEDED
          ),
          StageExecution(
            name = "Deploy",
            status = StageExecutionStatus.SUCCEEDED
          )
        ),
        status = PipelineExecutionStatus.SUCCEEDED,
        startedAt = ZonedDateTime.parse("2023-01-01T00:00:00Z")
      )
    }
  }

  object Failed {
    fun create(): PipelineExecution {
      return PipelineExecution(
        stages = listOf(
          StageExecution(
            name = "Build application",
            status = StageExecutionStatus.SUCCEEDED
          ),
          StageExecution(
            name = "Deploy",
            status = StageExecutionStatus.FAILED
          )
        ),
        status = PipelineExecutionStatus.FAILED,
        startedAt = ZonedDateTime.parse("2023-01-01T00:00:00Z")
      )
    }
  }

  object Running {
    fun create(): PipelineExecution {
      return PipelineExecution(
        stages = listOf(
          StageExecution(
            name = "Build application",
            status = StageExecutionStatus.SUCCEEDED
          ),
          StageExecution(
            name = "Deploy",
            status = StageExecutionStatus.RUNNING
          )
        ),
        status = PipelineExecutionStatus.RUNNING,
        startedAt = ZonedDateTime.parse("2023-01-01T00:00:00Z")
      )
    }
  }

  object StageMissing {
    fun create(): PipelineExecution {
      return PipelineExecution(
        stages = listOf(
          StageExecution(
            name = "Build application",
            status = StageExecutionStatus.SUCCEEDED
          )
        ),
        status = PipelineExecutionStatus.SUCCEEDED,
        startedAt = ZonedDateTime.parse("2023-01-01T00:00:00Z")
      )
    }
  }
}
