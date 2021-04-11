package com.zlrx.examples.genericservice.router

import com.zlrx.examples.genericservice.service.VehicleProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class IncrementRouter(
    private val service: VehicleProcessor
) {

    @Bean
    fun composedRouter() = coRouter {
        "/api/v1/increment".nest {
            GET("") {
                val response = service.incrementAllVehiclesEnginePower()
                ServerResponse.ok().bodyAndAwait(response)
            }
        }
    }
}