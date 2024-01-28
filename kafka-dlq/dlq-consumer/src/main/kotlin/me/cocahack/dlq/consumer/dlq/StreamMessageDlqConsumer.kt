package me.cocahack.dlq.consumer.dlq

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component


private val logger = KotlinLogging.logger {}

@Component
class StreamMessageDlqConsumer {

    @KafkaListener(
        topics = ["\${dlq.kafka.topic-name}"],
        containerFactory = "streamMessageKafkaListenerFactory"
    )
    fun createOrderListener(@Payload streamMessage: StreamMessage) {
        logger.info { "dlq 메시지 소비 - $streamMessage" }
    }
}