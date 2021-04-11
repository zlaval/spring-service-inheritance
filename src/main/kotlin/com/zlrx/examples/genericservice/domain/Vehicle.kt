package com.zlrx.examples.genericservice.domain

import com.zlrx.examples.genericservice.error.ServiceException
import org.springframework.http.HttpStatus


abstract class Vehicle {
    abstract val wheelNumber: Int

    //wheels should be the part of the collection in case of document db,
    //but we demonstrate a connection between collections in this demo
    abstract var wheels: MutableList<Wheel>
    abstract val wheelIds: MutableList<String>

    suspend fun installWheels(installableWheels: List<Wheel>, updateFn: suspend (List<Wheel>) -> Unit) {
        when {
            wheelIds.isNotEmpty() -> {
                throw ServiceException("Wheels have already been installed")
            }
            installableWheels.size == wheelNumber -> {
                wheels = installableWheels.toMutableList()
                putOnWheels()
                updateFn(wheels)
            }
            else -> {
                throw ServiceException("You have to install $wheelNumber wheels onto the ${getType()}", HttpStatus.BAD_REQUEST)
            }
        }
    }

    private fun putOnWheels() {
        wheels.forEachIndexed { index, wheel ->
            val place = WheelPlace.values()[index]
            wheel.place = place
            wheel.mounted = true
            wheel.description = "installed on ${getType()}"
            wheelIds.add(wheel._id!!)
        }
    }

    protected abstract fun getType(): String
}