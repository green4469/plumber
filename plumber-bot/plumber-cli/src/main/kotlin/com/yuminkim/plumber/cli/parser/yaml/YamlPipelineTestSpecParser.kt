package com.yuminkim.plumber.cli.parser.yaml

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.yuminkim.plumber.cli.loader.RawPipelineTestSpec
import com.yuminkim.plumber.cli.parser.PipelineTestSpecFormat
import com.yuminkim.plumber.cli.parser.PipelineTestSpecParser
import com.yuminkim.plumber.cli.specification.CliPipelineTestSpec
import org.springframework.stereotype.Component

@Component
class YamlPipelineTestSpecParser : PipelineTestSpecParser {
  private val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()

  override fun supports(rawSpec: RawPipelineTestSpec): Boolean {
    return rawSpec.format == PipelineTestSpecFormat.YAML
  }

  override fun parse(rawSpec: RawPipelineTestSpec): CliPipelineTestSpec {
    return mapper.readValue(rawSpec.spec, CliPipelineTestSpec::class.java)
  }
}
