package com.zlrx.examples.genericservice.service

import com.zlrx.examples.genericservice.domain.Vehicle
import com.zlrx.examples.genericservice.error.ServiceException
import com.zlrx.examples.genericservice.repository.VehicleRepository
import com.zlrx.examples.genericservice.repository.WheelRepository
import com.zlrx.examples.genericservice.utils.toList
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

class VehicleType {
    companion object {
        const val BUS = "BUS"
        const val CAR = "CAR"
        const val LORRY = "LORRY"
    }
}

abstract class VehicleService<T : Vehicle, RT : VehicleRepository<T>> {

    @Autowired
    protected lateinit var repository: RT

    @Autowired
    protected lateinit var wheelRepository: WheelRepository

    protected abstract val create: (String, Int) -> T

    suspend fun installWheels(id: String): T {
        val vehicle = repository.findById(id) ?: throw ServiceException("Not found", HttpStatus.NOT_FOUND)
        val wheels = wheelRepository.findNotInstalled().take(vehicle.wheelNumber).toList()
        vehicle.installWheels(wheels)
        val saved = repository.save(vehicle)
        vehicle.wheels.forEach {
            wheelRepository.save(it)
        }
        return saved
    }

    suspend fun searchVehicle(id: String): T {
        val vehicle = repository.findById(id) ?: throw ServiceException("Not found $id", HttpStatus.NOT_FOUND)
        return addWheelsToVehicle(vehicle)
    }

    suspend fun addVehicle(producer: String, engineCapacity: Int): T {
        val vehicle = create(producer, engineCapacity)
        return repository.save(vehicle)
    }

    fun findAll() = repository.findAll().map {
        addWheelsToVehicle(it)
    }

    private suspend fun addWheelsToVehicle(vehicle: T): T {
        if (vehicle.wheelIds.isNotEmpty()) {
            val wheels = wheelRepository.findByIdIn(vehicle.wheelIds)
            vehicle.wheels = wheels.toList()
        }
        return vehicle
    }

}