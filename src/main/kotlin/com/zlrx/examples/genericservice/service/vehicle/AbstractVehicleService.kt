package com.zlrx.examples.genericservice.service.vehicle

import com.zlrx.examples.genericservice.domain.Vehicle
import com.zlrx.examples.genericservice.error.ServiceException
import com.zlrx.examples.genericservice.repository.VehicleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

abstract class AbstractVehicleService<T : Vehicle, RT : VehicleRepository<T>> {

    @Autowired
    protected lateinit var repository: RT

    protected abstract val create: (String, Int) -> T

    suspend fun searchVehicle(id: String) = repository.findById(id) ?: throw ServiceException("Not found $id", HttpStatus.NOT_FOUND)

    suspend fun addVehicle(producer: String, engineCapacity: Int): T {
        val vehicle = create(producer, engineCapacity)
        return repository.save(vehicle)
    }

    fun findAll() = repository.findAll()

}