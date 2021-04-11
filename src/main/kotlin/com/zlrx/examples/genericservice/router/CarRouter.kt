package com.zlrx.examples.genericservice.router

import com.zlrx.examples.genericservice.service.CarService
import com.zlrx.examples.genericservice.utils.toInt
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CarRouter(
    private val carService: CarService
) {

    @Bean
    fun busRouterFn() = coRouter {
        "/api/v1/car".nest {
            GET("") {
                val filter = it.queryParam("min-engine-capacity")
                val response = if (filter.isPresent) {
                    carService.filterEngineCapacityGte(filter.toInt())
                } else {
                    carService.findAll()
                }

                ServerResponse.ok().bodyAndAwait(response)
            }
        }
    }

}