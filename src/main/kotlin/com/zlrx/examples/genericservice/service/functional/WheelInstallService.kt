package com.zlrx.examples.genericservice.service.functional

import com.zlrx.examples.genericservice.domain.Bus
import com.zlrx.examples.genericservice.domain.Car
import com.zlrx.examples.genericservice.domain.Lorry
import com.zlrx.examples.genericservice.domain.Vehicle
import com.zlrx.examples.genericservice.domain.WheelInventory
import com.zlrx.examples.genericservice.domain.WheelPlace
import com.zlrx.examples.genericservice.repository.BusRepository
import com.zlrx.examples.genericservice.repository.CarRepository
import com.zlrx.examples.genericservice.repository.LorryRepository
import org.springframework.stereotype.Service


//TODO create object
//find all in type
//install to all
///...
@Service
class WheelInstallService(
    private val busRepository: BusRepository,
    private val carRepository: CarRepository,
    private val lorryRepository: LorryRepository
) {


    private val installBusWheels: (Bus, (Int, String) -> List<WheelInventory>) -> Bus = { bus, generator ->
        val wheels = generator(bus.wheelNumber, "Bus")
        bus.copy(
            wheels = wheels
        )
    }

    private val installLorryWheels: suspend (Lorry) -> Lorry = {
        val wheels = wheelGeneratorFn(it.wheelNumber, "Lorry")
        it.copy(
            wheels = wheels,
            massTons = it.massTons + 1
        )
    }

    private val installCarWheels: (Car, (Int, String) -> List<WheelInventory>) -> Car = { car, generator ->
        val wheels = generator(car.wheelNumber, "Bus")
        car.copy(
            wheels = wheels
        )
    }

    private val wheelGeneratorFn: (Int, String) -> List<WheelInventory> = { count, name ->
        (1..count).map { index ->
            WheelInventory(size = 30, tension = 10, description = "$name wheel", place = getWheelPlace(index))
        }
    }

    suspend fun installWheelsOnAllVehicle(): List<Vehicle> {
        val vehicles = emptyList<Vehicle>()
        return vehicles.map {
            installWheelOnVehicle(it)
        }
    }

    private suspend fun saveLorryWithAction(lorry: Lorry, fn: suspend (Lorry) -> Lorry): Lorry {
        lorryRepository.delete(lorry)
        return lorryRepository.save(fn(lorry))
    }

    suspend fun installWheelOnVehicle(vehicle: Vehicle): Vehicle {
        return when (vehicle) {
            is Bus -> busRepository.save(installBusWheels(vehicle, wheelGeneratorFn))
            is Car -> carRepository.save(installCarWheels(vehicle) { count, _ ->
                (0 until count).map {
                    WheelInventory(size = 15, tension = 2, description = "Car wheel", place = getWheelPlace(it))
                }
            })
            is Lorry -> saveLorryWithAction(vehicle, installLorryWheels)
        }
    }

    private fun getWheelPlace(index: Int): WheelPlace = WheelPlace.values()[index]

}