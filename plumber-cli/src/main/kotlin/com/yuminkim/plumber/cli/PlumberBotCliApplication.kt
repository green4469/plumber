package com.yuminkim.plumber.cli

import com.yuminkim.plumber.cli.loader.FilePipelineTestSpecLoader
import com.yuminkim.plumber.cli.parser.PipelineTestSpecParser
import com.yuminkim.plumber.cli.specification.CliPipelineTestSpec
import com.yuminkim.plumber.core.model.specification.JenkinsTriggerPipelineConfig
import com.yuminkim.plumber.core.model.specification.PipelineTestSpec
import com.yuminkim.plumber.core.model.specification.StageTestSpec
import com.yuminkim.plumber.core.model.specification.Timeout
import com.yuminkim.plumber.core.model.specification.TriggerPipelineSpec
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlumberBotCliApplication(
  @Qualifier("compositePipelineTestSpecParser")
  private val pipelineTestSpecParser: PipelineTestSpecParser
) : ApplicationRunner {
  override fun run(args: ApplicationArguments) {
    logger.info("PlumberBotCliApplication is running")

    val botArgs = args.parseArgs()
    logger.info("args: $botArgs")

    val testSpec = parseSpecFile(botArgs.specFile)
    logger.info("testSpec: $testSpec")
  }

  private fun ApplicationArguments.parseArgs(): PlumberBotCliArguments {
    val specFile =
      this.getOptionValues("spec-file")?.get(0) ?: throw IllegalArgumentException(
        "spec-file is required"
      )
    return PlumberBotCliArguments(
      specFile = specFile
    )
  }

  private fun parseSpecFile(specFile: String): PipelineTestSpec {
    val rawSpec = FilePipelineTestSpecLoader.load(specFile)
    val cliTestSpec = pipelineTestSpecParser.parse(rawSpec)
    return cliTestSpec.toCoreModel()
  }

  private fun CliPipelineTestSpec.toCoreModel(): PipelineTestSpec {
    return with(this.pipeline) {
      PipelineTestSpec(
        name = this.name,
        description = this.description,
        trigger = TriggerPipelineSpec.jenkins(
          with(this.trigger.config) {
            JenkinsTriggerPipelineConfig(
              url = url,
              job = job,
              token = token
            )
          }
        ),
        stages = this.stages.map { stage ->
          StageTestSpec(
            name = stage.name,
            description = stage.description,
            expectedStatus = stage.expectedStatus
          )
        },
        expectedStatus = this.expectedStatus,
        timeout = Timeout.from(this.timeout)
      )
    }
  }

  companion object {
    private val logger = LoggerFactory.getLogger(this::class.java)
  }
}

fun main(args: Array<String>) {
  runApplication<PlumberBotCliApplication>(*args) {
    webApplicationType = WebApplicationType.NONE
  }
}
