package com.zlrx.examples.genericservice.service

import com.zlrx.examples.genericservice.configuration.VehicleServiceFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import org.springframework.stereotype.Service

@Service
class VehicleProcessor(
    private val serviceFactory: VehicleServiceFactory,

    //for example data loading
    private val busService: BusService,
    private val carService: CarService,
    private val lorryService: LorryService
) {

    //example of processing multitype vehicles
    suspend fun incrementAllVehiclesEnginePower(): Flow<Vehicle> {
        val vehicles = collectVehicles()
        return vehicles.onEach {
            it.engineCapacity += 100
            serviceFactory.getService(it.getType()).save(it)
        }
    }

    //load example data
    private fun collectVehicles(): Flow<Vehicle> {
        val buses: Flow<Vehicle> = busService.findAll()
        val cars: Flow<Vehicle> = carService.findAll()
        val lorries: Flow<Vehicle> = lorryService.findAll()
        return merge(buses, cars, lorries)
    }

}