package me.cocahack.dlq.consumer.dlq

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "dlq.kafka")
data class DlqKafkaProperties(
  val port: Int = 9092,
  val bootstrapAddresses: String = "localhost:9092",
)
