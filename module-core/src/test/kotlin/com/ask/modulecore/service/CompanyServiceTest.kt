package com.ask.modulecore.service

import com.ask.modulecore.config.JpaConfig
import com.ask.modulecore.entity.Company
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
@Import(JpaConfig::class)
@ActiveProfiles("core")
internal class CompanyServiceTest(
    @Autowired private val companyService: CompanyService
) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Test
    fun findByIdOrNull() {
        companyService.findByIdOrNull("company01")
            .apply {
                log.info("company: $this")
            }
    }

    @Test
    fun save() {
        companyService.save(Company(name = "company...."))
    }

    @Test
    fun saveAndFlush() {
        companyService.saveAndFlush(Company(name = "company...."))
    }

}
