package com.example.event

import com.example.entity.CharacterStatus
import com.example.entity.OutboxMessageEntity
import com.example.repository.CharacterRepository
import com.example.repository.OutboxMessageRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionalEventListener

val logger = KotlinLogging.logger {}

@Component
class CharacterEventListener(
    private val outboxMessageRepository: OutboxMessageRepository,
    private val characterRepository: CharacterRepository,
) {

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun handleEvent(event: ProgressCharacterEvent) {
        logger.info { "Handling ProgressCharacterEvent: $event" }

        val character = characterRepository.findByIdAndStatus(id = event.characterId, status = CharacterStatus.IN_PROGRESS)!!

        // 코루틴 사용하여 3.5 초 대기
//        runBlocking {
//            logger.info { "Sleeping for 3 seconds" }
//            kotlinx.coroutines.delay(3500)
//            logger.info { "Woke up after 3 seconds" }
//        }
//
//        logger.info { "Resuming..." }

        character.status = CharacterStatus.MESSAGED

        // 여기서 에러
        val outboxMessage = outboxMessageRepository.findByIdAndProcessedAtIsNull(event.outboxMessageId)!!
        outboxMessage.payload = mapOf("characterId" to event.characterId)

        outboxMessageRepository.save(outboxMessage)
        characterRepository.save(character)
    }
}