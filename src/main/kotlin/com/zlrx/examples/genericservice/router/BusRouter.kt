package com.zlrx.examples.genericservice.router

import com.zlrx.examples.genericservice.service.BusService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class BusRouter(
    private val busService: BusService
) {

    @Bean
    fun busRouterFn() = coRouter {
        "/api/v1/bus".nest {
            GET("") {
                val response = busService.findAll()
                ServerResponse.ok().bodyAndAwait(response)
            }
        }


    }

}