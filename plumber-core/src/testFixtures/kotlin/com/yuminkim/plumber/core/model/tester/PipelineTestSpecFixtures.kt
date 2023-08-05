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
        name = "test",
        description = "test",
        trigger = TriggerPipelineSpec.jenkins(
          config = JenkinsTriggerPipelineConfig(
            url = "",
            job = "",
            token = ""
          )
        ),
        stages = listOf(
          StageTestSpec(
            name = "test",
            description = "test",
            expectedStatus = StageTerminalStatus.SUCCESS
          )
        ),
        expectedStatus = PipelineTerminalStatus.SUCCESS
      )
    }
  }
}
