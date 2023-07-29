package com.yuminkim.plumber.cli.parser

import com.yuminkim.plumber.cli.loader.RawPipelineTestSpec
import com.yuminkim.plumber.cli.specification.CliPipelineTestSpec

interface PipelineTestSpecParser {
    fun supports(rawSpec: RawPipelineTestSpec): Boolean

    fun parse(rawSpec: RawPipelineTestSpec): CliPipelineTestSpec
}
