package com.yuminkim.plumber.cli.loader

import com.yuminkim.plumber.cli.parser.PipelineTestSpecFormat
import java.io.File

object FilePipelineTestSpecLoader {
  fun load(filePath: String): RawPipelineTestSpec {
    if (filePath.endsWith(".yaml") || filePath.endsWith(".yml")) {
      return loadYaml(filePath)
    }
    throw IllegalArgumentException("Unsupported file format")
  }

  private fun loadYaml(filePath: String): RawPipelineTestSpec {
    val fileContent: String = File(filePath).readText()
    return RawPipelineTestSpec(PipelineTestSpecFormat.YAML, fileContent)
  }
}
