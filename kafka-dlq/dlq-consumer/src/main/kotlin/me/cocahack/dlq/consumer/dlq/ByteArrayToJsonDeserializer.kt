package me.cocahack.dlq.consumer.dlq

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Deserializer

class ByteArrayToJsonDeserializer(
    private val objectMapper: ObjectMapper
) : Deserializer<StreamMessage> {


    override fun deserialize(topic: String?, data: ByteArray): StreamMessage {
        return objectMapper.readValue(String(data), StreamMessage::class.java)
    }
}