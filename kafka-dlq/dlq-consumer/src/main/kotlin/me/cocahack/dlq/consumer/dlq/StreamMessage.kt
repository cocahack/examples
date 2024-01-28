package me.cocahack.dlq.consumer.dlq

data class StreamMessage(
    val text: String,
    val shouldError: Boolean
)