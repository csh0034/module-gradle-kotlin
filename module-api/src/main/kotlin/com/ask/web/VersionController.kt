package com.ask.web

import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class VersionController {

    @GetMapping("/version.txt")
    fun version() = ResponseEntity.ok()
        .contentType(MediaType.TEXT_PLAIN)
        .body(ClassPathResource("META-INF/build-info.properties"))

}
