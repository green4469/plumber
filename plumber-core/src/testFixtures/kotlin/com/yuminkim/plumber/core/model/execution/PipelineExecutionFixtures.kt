package com.yuminkim.plumber.core.model.execution

import com.yuminkim.plumber.core.model.pipeline.PipelineStatus
import com.yuminkim.plumber.core.model.pipeline.StageStatus
import java.time.ZonedDateTime

object PipelineExecutionFixtures {
  object Failed {
    fun create(): PipelineExecution {
      return PipelineExecution(
        stages = listOf(
          StageExecution(
            // Stage test name has interdependency with pipeline test spec
            name = "Build application",
            status = StageStatus.FAILED,
          ),
          StageExecution(
            name = "Deploy",
            status = StageStatus.WAITING,
          ),
        ),
        status = PipelineStatus.FAILED,
        startedAt = ZonedDateTime.parse("2023-01-01T00:00:00Z"),
      )
    }
  }
}

