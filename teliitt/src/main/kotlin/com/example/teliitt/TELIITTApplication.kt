package com.example.teliitt

import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class TELIITTApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder(TELIITTApplication::class.java)
        .web(WebApplicationType.SERVLET)
        .profiles("local")
        .run(*args)
}
