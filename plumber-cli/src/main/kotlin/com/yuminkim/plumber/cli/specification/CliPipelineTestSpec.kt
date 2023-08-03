package com.yuminkim.plumber.cli.specification

import com.yuminkim.plumber.core.model.specification.PipelineEngineType
import com.yuminkim.plumber.core.model.specification.PipelineTerminalStatus
import com.yuminkim.plumber.core.model.specification.StageTerminalStatus

class CliPipelineTestSpec(
  val pipeline: Pipeline
)

class Pipeline(
  val name: String,
  val description: String,
  val trigger: Trigger,
  val stages: List<Stage>,
  val expectedStatus: PipelineTerminalStatus,
  val timeout: String
)

class Stage(
  val name: String,
  val description: String,
  val expectedStatus: StageTerminalStatus
)

class Trigger(
  val type: PipelineEngineType,
  val config: JenkinsTriggerConfig
)

class JenkinsTriggerConfig(
  val url: String,
  val job: String,
  val token: String
)
