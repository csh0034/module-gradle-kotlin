package com.ask.modulecore.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("custom")
@ConstructorBinding
data class CustomProperties(
    val version: String,
    val dbAddress: String
)
