package com.ask.modulecore.repository

import com.ask.modulecore.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String>, UserRepositoryCustom {

    fun findByLoginId(loginId: String): User?

}
