package com.ask.modulecore.service

import com.ask.modulecore.entity.Company
import com.ask.modulecore.repository.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyService(
    companyRepository: CompanyRepository
) : GenericService<Company, String>(
    companyRepository
) {

}
