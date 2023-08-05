package com.yuminkim.plumber.core.model.tester

import com.yuminkim.plumber.core.model.specification.JenkinsTriggerPipelineConfig
import com.yuminkim.plumber.core.model.specification.PipelineTerminalStatus
import com.yuminkim.plumber.core.model.specification.PipelineTestSpec
import com.yuminkim.plumber.core.model.specification.StageTerminalStatus
import com.yuminkim.plumber.core.model.specification.StageTestSpec
import com.yuminkim.plumber.core.model.specification.TriggerPipelineSpec

object PipelineTestSpecFixtures {
  object ExpectingSuccess {
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
            // Stage test name has interdependency with pipeline execution
            name = "Build application",
            description = "Expect Stage Succeeded",
            expectedStatus = StageTerminalStatus.SUCCEEDED
          )
        ),
        expectedStatus = PipelineTerminalStatus.SUCCEEDED
      )
    }
  }
}
