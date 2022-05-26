package com.ask.modulecore.config

import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan
class CoreConfig {

    private val log = LoggerFactory.getLogger(CoreConfig::class.java)

    @Bean
    fun appRunner(customProperties: CustomProperties) = ApplicationRunner() {
        log.info("customProperties: $customProperties")
    }

}
