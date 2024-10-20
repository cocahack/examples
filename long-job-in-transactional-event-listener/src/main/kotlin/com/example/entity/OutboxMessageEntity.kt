package com.example.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.Instant

@Entity(name = "outbox_message")
class OutboxMessageEntity(
    var processedAt: Instant? = null,
    val characterId: Long,
    @JdbcTypeCode(SqlTypes.JSON) @Column(columnDefinition = "jsonb") var payload: Map<String, Any?> = mapOf(),
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0
}