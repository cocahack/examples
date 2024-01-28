package me.cocahack.dlq.consumer

import org.springframework.stereotype.Service

@Service
class StreamProcessingService {

    fun process(message: StreamMessage) {
        if (!message.shouldError) {
            print(message.text)
        } else {
            error("DLQ 로 이동")
        }
    }
}