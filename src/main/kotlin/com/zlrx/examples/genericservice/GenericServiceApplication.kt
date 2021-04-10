package com.zlrx.examples.genericservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication(exclude = [MongoAutoConfiguration::class, WebMvcAutoConfiguration::class])
@EnableReactiveMongoRepositories(basePackages = ["com.zlrx"])
class GenericServiceApplication

fun main(args: Array<String>) {
    runApplication<GenericServiceApplication>(*args)
}
