package com.ask.modulecore

import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootApplication
@SpringBootTest
@ActiveProfiles("core")
class ModuleCoreApplicationTests {

    @Test
    fun contextLoads() {
    }

}
