package com.yuminkim.plumber.core.model.specification

class TriggerPipelineSpec private constructor(
    val type: PipelineEngineType,
    val config: TriggerPipelineConfig
) {
    companion object {
        fun jenkins(config: JenkinsTriggerPipelineConfig): TriggerPipelineSpec {
            return TriggerPipelineSpec(PipelineEngineType.JENKINS, config)
        }
    }
}

interface TriggerPipelineConfig

class JenkinsTriggerPipelineConfig(
    val url: String,
    val job: String,
    val token: String
) : TriggerPipelineConfig
