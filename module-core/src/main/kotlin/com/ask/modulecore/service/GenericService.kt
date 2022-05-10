package com.ask.modulecore.service

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import java.io.Serializable

abstract class GenericService<T : Any, ID : Serializable>(
    private val jpaRepository: JpaRepository<T, ID>
) {

    fun findByIdOrNull(id: ID) = jpaRepository.findByIdOrNull(id)

    fun save(entity: T) = jpaRepository.save(entity)

    fun saveAndFlush(entity: T) = jpaRepository.saveAndFlush(entity)

}
