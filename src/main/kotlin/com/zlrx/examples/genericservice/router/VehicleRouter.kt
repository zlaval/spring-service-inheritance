package com.zlrx.examples.genericservice.router

import com.zlrx.examples.genericservice.domain.Wheel
import com.zlrx.examples.genericservice.model.VehicleRequest
import com.zlrx.examples.genericservice.model.WheelRequest
import com.zlrx.examples.genericservice.repository.WheelRepository
import com.zlrx.examples.genericservice.service.CarService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class VehicleRouter(
    private val carService: CarService,
    private val wheelRepository: WheelRepository
) {


    @Bean
    fun router() = coRouter {

        "/api/v1".nest {
            "/wheel".nest {
                GET("") {
                    ServerResponse.ok().bodyAndAwait(wheelRepository.findAll())
                }

                POST("") {
                    val request = it.awaitBody<WheelRequest>()
                    val wheel = Wheel(size = request.size, tension = request.tension)
                    val response = wheelRepository.save(wheel)
                    ServerResponse.ok().bodyValueAndAwait(response)
                }
            }

            "/vehicle".nest {

                GET("") {
                    val result = carService.findAll()
                    ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyAndAwait(result)
                }

                GET("/{id}") {
                    val id = it.pathVariable("id")
                    val result = carService.searchVehicle(id)
                    ServerResponse.ok().bodyValueAndAwait(result)
                }

                POST("") {
                    val request = it.awaitBody<VehicleRequest>()
                    val response = carService.addVehicle(request.producer, request.engineCapacity)
                    ServerResponse.ok().bodyValueAndAwait(response)
                }

                PUT("/install-wheels/{id}") {
                    val id = it.pathVariable("id")
                    val response = carService.installWheels(id)
                    ServerResponse.ok().bodyValueAndAwait(response)
                }

            }
        }
    }
}