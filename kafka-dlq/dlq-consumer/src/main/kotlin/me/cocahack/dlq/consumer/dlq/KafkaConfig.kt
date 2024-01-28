package me.cocahack.dlq.consumer.dlq

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.ByteArrayDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer


@Configuration
@EnableConfigurationProperties(DlqKafkaProperties::class)
class KafkaConfig {

    @Bean
    fun objectMapper(): ObjectMapper =
        ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .registerModule(JavaTimeModule())
            .registerKotlinModule()

    @Bean
    fun consumerFactory(
        dlqKafkaProperties: DlqKafkaProperties,
        objectMapper: ObjectMapper
    ): ConsumerFactory<String, StreamMessage> {
        val config: MutableMap<String, Any> = HashMap()
        config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = dlqKafkaProperties.bootstrapAddresses
        config[ConsumerConfig.GROUP_ID_CONFIG] = "dlt-consumer"
        config[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        config[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = ByteArrayToJsonDeserializer::class.java
        return DefaultKafkaConsumerFactory(
            config, StringDeserializer(),
            ByteArrayToJsonDeserializer(objectMapper)
        )
    }

    @Bean
    fun streamMessageKafkaListenerFactory(
        dlqKafkaProperties: DlqKafkaProperties,
        objectMapper: ObjectMapper,
    ): ConcurrentKafkaListenerContainerFactory<String, StreamMessage> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, StreamMessage> =
            ConcurrentKafkaListenerContainerFactory<String, StreamMessage>()
        factory.consumerFactory = consumerFactory(dlqKafkaProperties, objectMapper)
        return factory
    }

}
