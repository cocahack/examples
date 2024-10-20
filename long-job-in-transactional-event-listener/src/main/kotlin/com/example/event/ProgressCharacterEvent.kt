package com.example.event

data class ProgressCharacterEvent(
    val characterId: Long,
    val outboxMessageId: Long,
)