package com.example.repository

import com.example.entity.OutboxMessageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OutboxMessageRepository : JpaRepository<OutboxMessageEntity, Long> {
    fun findByIdAndProcessedAtIsNull(id: Long): OutboxMessageEntity?

    fun findByCharacterId(characterId: Long): OutboxMessageEntity?
}