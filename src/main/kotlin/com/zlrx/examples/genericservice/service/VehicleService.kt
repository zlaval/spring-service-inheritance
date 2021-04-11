package com.zlrx.examples.genericservice.service

import com.zlrx.examples.genericservice.domain.Vehicle
import com.zlrx.examples.genericservice.error.ServiceException
import com.zlrx.examples.genericservice.repository.VehicleRepository
import com.zlrx.examples.genericservice.repository.WheelInventoryRepository
import com.zlrx.examples.genericservice.utils.toList
import kotlinx.coroutines.flow.take
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

abstract class VehicleService<T : Vehicle, RT : VehicleRepository<T>> {

    @Autowired
    protected lateinit var repository: RT

    @Autowired
    protected lateinit var wheelInventoryRepository: WheelInventoryRepository

    protected abstract val create: (String, Int) -> T

    suspend fun installWheels(id: String): T {
        val vehicle = repository.findById(id) ?: throw ServiceException("Not found", HttpStatus.NOT_FOUND)
        val wheels = wheelInventoryRepository.findAll().take(vehicle.wheelNumber).toList()
        vehicle.installWheels(wheels)
        val result = repository.save(vehicle)
        wheelInventoryRepository.deleteAll(wheels)
        return result
    }

    suspend fun searchVehicle(id: String) = repository.findById(id) ?: throw ServiceException("Not found $id", HttpStatus.NOT_FOUND)

    suspend fun addVehicle(producer: String, engineCapacity: Int): T {
        val vehicle = create(producer, engineCapacity)
        return repository.save(vehicle)
    }

    fun findAll() = repository.findAll()

}