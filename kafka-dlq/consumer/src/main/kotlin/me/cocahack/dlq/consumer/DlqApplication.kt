package me.cocahack.dlq.consumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DlqApplication

fun main(args: Array<String>) {
	runApplication<DlqApplication>(*args)
}
