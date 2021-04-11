package com.zlrx.examples.genericservice.router

import com.zlrx.examples.genericservice.domain.WheelInventory
import com.zlrx.examples.genericservice.model.WheelRequest
import com.zlrx.examples.genericservice.repository.WheelInventoryRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class WheelRouter(
    private val wheelInventoryRepository: WheelInventoryRepository
) {

    @Bean
    fun wheelRouterFn() = coRouter {
        "/api/v1/wheel".nest {
            GET("") {
                ServerResponse.ok().bodyAndAwait(wheelInventoryRepository.findAll())
            }

            POST("") {
                val request = it.awaitBody<WheelRequest>()
                val wheel = WheelInventory(size = request.size, tension = request.tension)
                val response = wheelInventoryRepository.save(wheel)
                ServerResponse.ok().bodyValueAndAwait(response)
            }
        }
    }

}