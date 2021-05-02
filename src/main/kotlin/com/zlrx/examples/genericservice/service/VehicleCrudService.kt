package com.zlrx.examples.genericservice.service

import com.zlrx.examples.genericservice.domain.Vehicle
import com.zlrx.examples.genericservice.model.VehicleRequest
import com.zlrx.examples.genericservice.service.vehicle.BusService
import com.zlrx.examples.genericservice.service.vehicle.CarService
import com.zlrx.examples.genericservice.service.vehicle.LorryService
import com.zlrx.examples.genericservice.utils.toList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.springframework.stereotype.Service

@Service
class VehicleCrudService(
    private val busService: BusService,
    private val lorryService: LorryService,
    private val carService: CarService
) {

    suspend fun findAll(): Flow<Vehicle> {
        val buses = busService.findAll().toList()
        val lorries = lorryService.findAll().toList()
        val cars = carService.findAll().toList()
        return (buses + lorries + cars).asFlow()
    }

    suspend fun addVehicle(type: String, request: VehicleRequest) {
        when (type) {
            "car" -> carService.addVehicle(request.producer, request.engineCapacity)
            "bus" -> busService.addVehicle(request.producer, request.engineCapacity)
            "lorry" -> lorryService.addVehicle(request.producer, request.engineCapacity)
        }
    }

}