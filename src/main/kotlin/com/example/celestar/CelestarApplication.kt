package com.example.celestar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [MongoAutoConfiguration::class])
@EnableConfigurationProperties
class CelestarApplication

fun main(args: Array<String>) {
	runApplication<CelestarApplication>(*args)
}
