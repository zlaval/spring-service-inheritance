package com.zlrx.examples.genericservice.service

import com.zlrx.examples.genericservice.domain.Vehicle
import com.zlrx.examples.genericservice.error.ServiceException
import com.zlrx.examples.genericservice.repository.VehicleRepository
import com.zlrx.examples.genericservice.repository.WheelRepository
import com.zlrx.examples.genericservice.utils.toList
import org.springframework.http.HttpStatus

abstract class VehicleService<T : Vehicle, RT : VehicleRepository<T>>(
    private val repository: RT,
    private val wheelRepository: WheelRepository
) {

    suspend fun installWheels(id: String) {
        val vehicle = repository.findById(id) ?: throw ServiceException("Not found", HttpStatus.NOT_FOUND)
        val wheels = wheelRepository.findNotInstalled(vehicle.wheelNumber).toList()
        vehicle.addWheels(wheels)
    }

}