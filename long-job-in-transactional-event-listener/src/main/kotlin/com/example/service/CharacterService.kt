package com.example.service

import com.example.controller.CharacterResponse
import com.example.controller.OutboxMessageResponse
import com.example.entity.CharacterEntity
import com.example.entity.CharacterStatus
import com.example.entity.OutboxMessageEntity
import com.example.event.ProgressCharacterEvent
import com.example.repository.CharacterRepository
import com.example.repository.OutboxMessageRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CharacterService(
    private val characterRepository: CharacterRepository,
    private val outboxMessageRepository: OutboxMessageRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
) {

    @Transactional
    fun createCharacter(name: String): CharacterEntity {
        val character = CharacterEntity(name, CharacterStatus.INACTIVE)
        return characterRepository.save(character)
    }

    @Transactional
    fun processCharacter(characterId: Long) {
        val character = requireNotNull(characterRepository.findByIdOrNull(characterId)) { "Character not found" }
        character.status = CharacterStatus.IN_PROGRESS
//        characterRepository.save(character)

        val outboxMessage = OutboxMessageEntity(characterId = character.id)

        outboxMessageRepository.save(outboxMessage)
        applicationEventPublisher.publishEvent(ProgressCharacterEvent(characterId, outboxMessage.id))
    }

    fun getCharacter(characterId: Long): CharacterResponse {
        val character = requireNotNull(characterRepository.findByIdOrNull(characterId))
        val outboxMessage = outboxMessageRepository.findByCharacterId(character.id)

        return CharacterResponse(
            id = character.id,
            name = character.name,
            status = character.status,
            message = outboxMessage?.let {
                OutboxMessageResponse(outboxMessage.id)
            }
        )
    }
}