package com.yuminkim.plumber.core.model.executor

import com.yuminkim.plumber.core.model.execution.PipelineExecution
import com.yuminkim.plumber.core.model.specification.TriggerPipelineSpec

interface PipelineExecutor {
  fun trigger(config: TriggerPipelineSpec): PipelineExecutionReference
  fun get(pipelineExecutionReference: PipelineExecutionReference): PipelineExecution
}
