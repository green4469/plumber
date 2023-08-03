package com.yuminkim.plumber.cli.parser

import com.yuminkim.plumber.cli.loader.RawPipelineTestSpec
import com.yuminkim.plumber.cli.specification.CliPipelineTestSpec
import org.springframework.stereotype.Component

@Component
class CompositePipelineTestSpecParser(
  private val parsers: List<PipelineTestSpecParser>
) : PipelineTestSpecParser {
  override fun supports(rawSpec: RawPipelineTestSpec): Boolean {
    return parsers.any { it.supports(rawSpec) }
  }

  override fun parse(rawSpec: RawPipelineTestSpec): CliPipelineTestSpec {
    val parser = parsers.find { it.supports(rawSpec) }
      ?: throw IllegalArgumentException("No parser found for YAML format")
    return parser.parse(rawSpec)
  }
}
