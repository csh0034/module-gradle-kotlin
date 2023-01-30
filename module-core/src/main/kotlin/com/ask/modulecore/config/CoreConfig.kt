package com.ask.modulecore.config

import com.ask.modulecore.util.logger
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan
class CoreConfig {

  private val log = logger()

  @Bean
  fun appRunner(customProperties: CustomProperties) = ApplicationRunner {
    log.info("customProperties: $customProperties")
  }

}
