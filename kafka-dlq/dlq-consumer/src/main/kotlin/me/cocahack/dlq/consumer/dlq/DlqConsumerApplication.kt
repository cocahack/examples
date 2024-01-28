package me.cocahack.dlq.consumer.dlq

import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class DlqConsumerApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder(DlqConsumerApplication::class.java)
        .web(WebApplicationType.SERVLET)
        .profiles("local")
        .run(*args)
}
