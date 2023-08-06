package com.yuminkim.plumber.core.model.specification

data class TriggerPipelineSpec internal constructor(
  val type: PipelineEngineType,
  val config: TriggerPipelineConfig
) {
  companion object {
    fun jenkins(config: JenkinsTriggerPipelineConfig): TriggerPipelineSpec {
      return TriggerPipelineSpec(PipelineEngineType.JENKINS, config)
    }
  }
}

enum class PipelineEngineType {
  JENKINS
}

interface TriggerPipelineConfig

data class JenkinsTriggerPipelineConfig(
  val url: String,
  val job: String,
  val token: String
) : TriggerPipelineConfig
