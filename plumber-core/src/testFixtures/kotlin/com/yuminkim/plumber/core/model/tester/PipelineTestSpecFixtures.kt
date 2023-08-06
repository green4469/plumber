package com.yuminkim.plumber.core.model.tester

import com.yuminkim.plumber.core.model.specification.ExpectedPipelineTerminalStatus
import com.yuminkim.plumber.core.model.specification.ExpectedStageTerminalStatus
import com.yuminkim.plumber.core.model.specification.JenkinsTriggerPipelineConfig
import com.yuminkim.plumber.core.model.specification.PipelineTestSpec
import com.yuminkim.plumber.core.model.specification.StageTestSpec
import com.yuminkim.plumber.core.model.specification.TriggerPipelineSpec

object PipelineTestSpecFixtures {
  object ExpectingSucceeded {
    fun create(): PipelineTestSpec {
      return PipelineTestSpec(
        name = "Test Pipeline",
        description = "Expect Single Stage and Pipeline Succeeded",
        trigger = TriggerPipelineSpec.jenkins(
          config = JenkinsTriggerPipelineConfig(
            url = "http://localhost:8080",
            job = "jenkins-job",
            token = "1s158z7wew"
          )
        ),
        stages = listOf(
          StageTestSpec(
            name = "Build application",
            description = "Expect Stage Succeeded",
            expectedStatus = ExpectedStageTerminalStatus.SUCCEEDED
          ),
          StageTestSpec(
            name = "Deploy",
            description = "Expect Stage Succeeded",
            expectedStatus = ExpectedStageTerminalStatus.SUCCEEDED
          )
        ),
        expectedStatus = ExpectedPipelineTerminalStatus.SUCCEEDED
      )
    }
  }
}
