package com.ask.modulecore.db

import com.ask.modulecore.util.logger
import org.junit.jupiter.api.*
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@TestClassOrder(ClassOrderer.OrderAnnotation::class)
@ActiveProfiles("core")
internal class InitializeSchema {

  private val log = logger()

  @Nested
  @Order(1)
  @SpringBootTest
  inner class Drop {

    @Test
    fun drop() {
      log.info("drop schema")
    }

  }

  @Nested
  @Order(2)
  @SpringBootTest
  inner class Create {

    @Test
    fun create() {
      log.info("create schema")
    }

  }

  companion object {

    private const val DB_NAME_KEY = "db.name"

    private val log = LoggerFactory.getLogger(InitializeSchema::class.java)
    private val dbName = System.getProperty(DB_NAME_KEY)

    @BeforeAll
    @JvmStatic
    fun beforeAll() {
      checkRequiredSystemProperty()
      printRequiredSystemProperty()
    }

    private fun checkRequiredSystemProperty() {
      if (dbName == null || dbName.isBlank()) {
        Assertions.fail<Any>(String.format("'%s' must not be blank", DB_NAME_KEY))
      }
    }

    private fun printRequiredSystemProperty() {
      log.info("'{}': {}", DB_NAME_KEY, dbName)
    }

  }

}
