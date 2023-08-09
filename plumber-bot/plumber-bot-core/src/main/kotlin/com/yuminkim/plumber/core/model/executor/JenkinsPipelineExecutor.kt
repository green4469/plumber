package com.yuminkim.plumber.core.model.executor

import com.yuminkim.plumber.core.model.execution.PipelineExecution
import com.yuminkim.plumber.core.model.specification.TriggerPipelineSpec
import org.springframework.stereotype.Component

@Component
class JenkinsPipelineExecutor : PipelineExecutor {
  override fun trigger(config: TriggerPipelineSpec): PipelineExecutionReference {
    TODO("Not yet implemented")
  }

  override fun get(pipelineExecutionReference: PipelineExecutionReference): PipelineExecution {
    TODO("Not yet implemented")
  }
}
