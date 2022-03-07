package com.ask.moduleapi

import com.ask.modulecore.entity.Company
import com.ask.modulecore.repository.CompanyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class InitializeRunner(
    private val companyRepository: CompanyRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        companyRepository.save(Company(name = "api..."))
    }

}
