package com.yuminkim.plumber.cli.specification

import com.yuminkim.plumber.core.model.specification.ExpectedPipelineTerminalStatus
import com.yuminkim.plumber.core.model.specification.ExpectedStageTerminalStatus
import com.yuminkim.plumber.core.model.specification.PipelineEngineType

class CliPipelineTestSpec(
  val pipeline: Pipeline
)

class Pipeline(
  val name: String,
  val description: String,
  val trigger: Trigger,
  val stages: List<Stage>,
  val expectedStatus: ExpectedPipelineTerminalStatus,
  val timeout: String
)

class Stage(
  val name: String,
  val description: String,
  val expectedStatus: ExpectedStageTerminalStatus
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
