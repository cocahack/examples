package me.cocahack.example.transactional

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class TransactionExampleApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder(TransactionExampleApplication::class.java)
        .profiles("local")
        .run(*args)
}

