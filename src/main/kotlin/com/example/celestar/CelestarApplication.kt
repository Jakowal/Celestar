package com.example.celestar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.runApplication

//Remove the 'exclude' for local running
@SpringBootApplication(exclude = [MongoAutoConfiguration::class])
class CelestarApplication

fun main(args: Array<String>) {
	runApplication<CelestarApplication>(*args)
}
