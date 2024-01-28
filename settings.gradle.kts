pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "examples"

include("kafka-dlq")
include("kafka-dlq:consumer")
include("kafka-dlq:dlq-consumer")
