package me.cocahack.dlq.consumer

data class StreamMessage(
    val text: String,
    val shouldError: Boolean
)