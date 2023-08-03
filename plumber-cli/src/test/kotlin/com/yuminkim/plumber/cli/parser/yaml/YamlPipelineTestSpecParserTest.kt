package com.yuminkim.plumber.cli.parser.yaml

import com.yuminkim.plumber.cli.loader.FilePipelineTestSpecLoader
import com.yuminkim.plumber.core.model.specification.PipelineEngineType
import com.yuminkim.plumber.core.model.specification.PipelineTerminalStatus
import com.yuminkim.plumber.core.model.specification.StageTerminalStatus
import io.kotest.assertions.asClue
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class YamlPipelineTestSpecParserTest : DescribeSpec({
  val parser = YamlPipelineTestSpecParser()

  describe("parse") {
    context("when the spec is valid") {
      val rawSpec = FilePipelineTestSpecLoader.load(
        "src/test/resources/spec/yaml/0001_valid-pipeline-spec.yaml"
      )

      it("should return pipeline spec") {
        val spec = parser.parse(rawSpec)

        spec.pipeline.asClue {
          it.name shouldBe "0001_valid-pipeline-spec"
          it.description shouldBe "Test CLI parsing logic"
          it.expectedStatus shouldBe PipelineTerminalStatus.SUCCESS
          it.timeout shouldBe "30m"
        }

        spec.pipeline.trigger.asClue {
          it.type shouldBe PipelineEngineType.JENKINS
          it.config.url shouldBe "http://localhost:8080"
          it.config.job shouldBe "test"
          it.config.token shouldBe "1s158z7wew"
        }

        spec.pipeline.stages.asClue {
          it.size shouldBe 2

          it[0].name shouldBe "build"
          it[0].description shouldBe "Build stage"
          it[0].expectedStatus shouldBe StageTerminalStatus.SUCCESS

          it[1].name shouldBe "deploy"
          it[1].description shouldBe "Deploy stage"
          it[1].expectedStatus shouldBe StageTerminalStatus.SUCCESS
        }
      }
    }
  }
})
