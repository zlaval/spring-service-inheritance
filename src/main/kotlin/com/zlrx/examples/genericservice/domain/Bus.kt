package com.zlrx.examples.genericservice.domain

import com.zlrx.examples.genericservice.error.ServiceException
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.http.HttpStatus

data class Bus(
    val producer: String,
    val licencePlate: String,
    val engineCapacity: Int
) : Vehicle {

    @DBRef(lazy = true)
    override val wheels: MutableMap<WheelPlace, Wheel> = mutableMapOf()

    @Transient
    override val wheelNumber: Int = 6

    override fun addWheels(installableWheels: List<Wheel>) {
        when (installableWheels.size) {
            wheelNumber -> installableWheels.forEachIndexed { i, w ->
                wheels[BusWheelPlace.values()[i]] = w
            }
            else -> throw ServiceException("You have to install $wheelNumber wheels onto the car", HttpStatus.BAD_REQUEST)
        }
    }

}
