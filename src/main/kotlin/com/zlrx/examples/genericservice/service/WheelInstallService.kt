package com.zlrx.examples.genericservice.service

import com.zlrx.examples.genericservice.domain.Bus
import com.zlrx.examples.genericservice.domain.Car
import com.zlrx.examples.genericservice.domain.Lorry
import com.zlrx.examples.genericservice.domain.Vehicle
import com.zlrx.examples.genericservice.domain.Wheel
import com.zlrx.examples.genericservice.domain.getWheelPlace
import com.zlrx.examples.genericservice.domain.installBusWheels
import com.zlrx.examples.genericservice.domain.installCarWheels
import com.zlrx.examples.genericservice.domain.installLorryWheels
import com.zlrx.examples.genericservice.domain.wheelGeneratorFn
import com.zlrx.examples.genericservice.repository.BusRepository
import com.zlrx.examples.genericservice.repository.CarRepository
import com.zlrx.examples.genericservice.repository.LorryRepository
import com.zlrx.examples.genericservice.utils.toList
import org.springframework.stereotype.Service

@Service
class WheelInstallService(
    private val busRepository: BusRepository,
    private val carRepository: CarRepository,
    private val lorryRepository: LorryRepository
) {

    suspend fun installWheelsOnAllVehicle(): List<Vehicle> {
        val vehicles = getAllVehicle()
        return vehicles.map {
            installWheelOnVehicle(it)
        }
    }

    private suspend fun getAllVehicle(): List<Vehicle> {
        val buses = busRepository.findAll().toList()
        val lorries = lorryRepository.findAll().toList()
        val cars = carRepository.findAll().toList()
        return buses + lorries + cars
    }

    private suspend fun saveLorryWithAction(lorry: Lorry, fn: suspend (Lorry) -> Lorry): Lorry {
        lorryRepository.delete(lorry)
        return lorryRepository.save(fn(lorry))
    }

    private suspend fun installWheelOnVehicle(vehicle: Vehicle): Vehicle {
        return when (vehicle) {
            is Bus -> busRepository.save(installBusWheels(vehicle, wheelGeneratorFn))
            is Car -> carRepository.save(installCarWheels(vehicle) { count, _ ->
                (0 until count).map {
                    Wheel(size = 15, tension = 2, description = "Car wheel", place = getWheelPlace(it))
                }
            })
            is Lorry -> saveLorryWithAction(vehicle, installLorryWheels)
        }
    }

}