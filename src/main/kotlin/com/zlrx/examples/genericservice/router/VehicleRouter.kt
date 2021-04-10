package com.zlrx.examples.genericservice.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class VehicleRouter {


    @Bean
    fun router() = coRouter {

        "/api/v1".nest {
            GET("/vehicle") {

                ServerResponse.ok().buildAndAwait()
            }

            POST("/vehicle") {

                ServerResponse.ok().buildAndAwait()
            }

            PUT("/vehicle/install-wheels") {

                ServerResponse.ok().buildAndAwait()
            }

        }
    }
}