package com.zlrx.examples.genericservice.service

import com.zlrx.examples.genericservice.configuration.VehicleServiceFactory
import com.zlrx.examples.genericservice.domain.Vehicle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import org.springframework.stereotype.Service

@Service
class ExampleComposedService(
    private val serviceFactory: VehicleServiceFactory,
    private val busService: BusService,
    private val carService: CarService,
    private val lorryService: LorryService
) {

    suspend fun increaseAllVehicleEnginePower(): Flow<Vehicle> {
        val vehicles = collectVehicles()
        return vehicles.map {
            it.engineCapacity += 100
            it
        }.onEach {
            serviceFactory.getService(it.getType()).save(it)
        }
    }

    private fun collectVehicles(): Flow<Vehicle> {
        val buses: Flow<Vehicle> = busService.findAll()
        val cars: Flow<Vehicle> = carService.findAll()
        val lorries: Flow<Vehicle> = lorryService.findAll()
        return merge(buses, cars, lorries)
    }

}