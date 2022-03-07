package com.ask.modulecore.repository

import com.ask.modulecore.entity.Company
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository : JpaRepository<Company, String> {

}
