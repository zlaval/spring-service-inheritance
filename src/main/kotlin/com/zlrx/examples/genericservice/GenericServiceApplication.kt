package com.zlrx.examples.genericservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GenericServiceApplication

fun main(args: Array<String>) {
    runApplication<GenericServiceApplication>(*args)
}
