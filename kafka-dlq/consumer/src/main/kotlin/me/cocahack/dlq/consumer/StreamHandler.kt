package me.cocahack.dlq.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.integration.handler.AbstractMessageHandler
import org.springframework.messaging.Message
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class StreamHandler(
    private val streamProcessingService: StreamProcessingService,
    private val objectMapper: ObjectMapper,
) : AbstractMessageHandler() {

    override fun handleMessageInternal(message: Message<*>) {

        val streamMessage: StreamMessage =
            objectMapper.readValue(String(message.payload as ByteArray), StreamMessage::class.java)

        logger.info { "Payload - $streamMessage" }

        streamProcessingService.process(streamMessage)
    }
}