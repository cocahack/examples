package com.example

import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class TransactionalEventListenerFailApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder(TransactionalEventListenerFailApplication::class.java)
        .web(WebApplicationType.SERVLET)
        .profiles("local")
        .run(*args)
}
