package com.zlrx.examples.genericservice.domain

import com.zlrx.examples.genericservice.error.ServiceException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus

abstract class Vehicle {
    abstract val wheelNumber: Int
    abstract var engineCapacity: Int

    abstract var wheels: MutableList<WheelInventory>

    @org.springframework.data.annotation.Transient
    private val logger = LoggerFactory.getLogger(javaClass)

    fun putWheels(installableWheels: List<WheelInventory>) {
        when {
            wheels.isNotEmpty() -> {
                throw ServiceException("Wheels have already been installed")
            }
            installableWheels.size == wheelNumber -> {
                wheels = installableWheels.toMutableList()
                setWheelsProps()
            }
            else -> {
                logger.error("Not enough wheels")
                throw ServiceException("You have to install $wheelNumber wheels onto the ${getType()}", HttpStatus.BAD_REQUEST)
            }
        }
    }

    private fun setWheelsProps() {
        wheels.forEachIndexed { index, wheel ->
            val place = WheelPlace.values()[index]
            wheel.place = place
            wheel.description = getType()
        }
    }

    abstract fun getType(): String
}