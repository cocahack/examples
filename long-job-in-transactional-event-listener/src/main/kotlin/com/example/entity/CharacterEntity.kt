package com.example.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "character")
class CharacterEntity(
    val name: String,
    @Enumerated(EnumType.STRING) var status: CharacterStatus,
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0
}

enum class CharacterStatus {
    ACTIVE,
    INACTIVE,
    IN_PROGRESS,
    MESSAGED,
    DELETED,
}
