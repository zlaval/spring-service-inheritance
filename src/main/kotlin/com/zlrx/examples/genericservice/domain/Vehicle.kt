package com.zlrx.examples.genericservice.domain

import com.zlrx.examples.genericservice.error.ServiceException
import org.springframework.http.HttpStatus

data class Wheel(
    val id: String,
    val size: Int,
    val tension: Int,
    var place: WheelPlace? = null
)

abstract class Vehicle {
    abstract val wheelNumber: Int

    abstract var wheels: MutableList<Wheel>

    fun installWheels(installableWheels: List<WheelInventory>) {
        when {
            wheels.isNotEmpty() -> {
                throw ServiceException("Wheels have already been installed")
            }
            installableWheels.size == wheelNumber -> {
                wheels = installableWheels.mapIndexed { index, wheel ->
                    Wheel(wheel._id!!, wheel.size, wheel.tension, WheelPlace.values()[index])
                }.toMutableList()
            }
            else -> {
                throw ServiceException("You have to install $wheelNumber wheels onto the ${getType()}", HttpStatus.BAD_REQUEST)
            }
        }
    }

    protected abstract fun getType(): String
}