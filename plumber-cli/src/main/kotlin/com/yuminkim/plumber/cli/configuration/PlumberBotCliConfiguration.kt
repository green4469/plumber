package com.yuminkim.plumber.cli.configuration

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(
  basePackages = [
    "com.yuminkim.plumber"
  ]
)
@ConfigurationPropertiesScan(
  basePackages = [
    "com.yuminkim.plumber"
  ]
)
class PlumberBotCliConfiguration
