package com.ask.modulecore.repository

import com.ask.modulecore.config.JpaConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@Import(JpaConfig::class)
@ActiveProfiles("core")
internal class UserRepositoryTest @Autowired constructor(
    private val userRepository: UserRepository
) {

    @Test
    fun count() {
        assertThat(userRepository.count()).isEqualTo(3)
    }

    @Test
    fun findByLoginId() {
        val user = userRepository.findByLoginId("sample")
        assertThat(user).isNull()
    }

    @Test
    fun findAllByCompanyName() {
        val users = userRepository.findAllByCompanyName("cname")
        assertThat(users).hasSize(3)

        println(users)
    }
}
