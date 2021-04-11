package com.zlrx.examples.genericservice.router

import com.zlrx.examples.genericservice.service.ExampleComposedService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ExampleComposedRouter(
    private val service: ExampleComposedService
) {

    @Bean
    fun composedRouter() = coRouter {
        "/api/v1/composed".nest {
            GET("") {
                val response = service.increaseAllVehicleEnginePower()
                ServerResponse.ok().bodyAndAwait(response)
            }
        }
    }
}