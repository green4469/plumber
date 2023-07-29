package com.yuminkim.plumber.cli

import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlumberBotCliApplication : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        logger.info("PlumberBotCliApplication is running")
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