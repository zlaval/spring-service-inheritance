package com.zlrx.examples.genericservice.router

import com.zlrx.examples.genericservice.model.VehicleRequest
import com.zlrx.examples.genericservice.service.VehicleCrudService
import com.zlrx.examples.genericservice.service.WheelInstallService
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
    private val wheelInstallService: WheelInstallService,
    private val vehicleCrudService: VehicleCrudService
) {

    @Bean
    fun vehicleRouterFn() = coRouter {
        "/api/v1/vehicle".nest {

            GET("") {
                val result = vehicleCrudService.findAll()
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyAndAwait(result)
            }

            POST("/{type}") {
                val type = it.pathVariable("type")
                val request = it.awaitBody<VehicleRequest>()
                val response = vehicleCrudService.addVehicle(type, request)
                ServerResponse.ok().bodyValueAndAwait(response)
            }

            PUT("/install-wheels") {
                val response = wheelInstallService.installWheelsOnAllVehicle()
                ServerResponse.ok().bodyValueAndAwait(response)
            }
        }
    }
}