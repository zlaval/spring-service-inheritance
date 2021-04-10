package com.zlrx.examples.genericservice.domain

import com.zlrx.examples.genericservice.error.ServiceException
import org.springframework.http.HttpStatus

data class Car(
    val producer: String,
    val licencePlate: String,
    val engineCapacity: Int,
    override val wheelNumber: Int,
    override val wheels: MutableMap<WheelPlace, Wheel> = mutableMapOf()

) : Vehicle {

    override fun addWheels(installableWheels: List<Wheel>) {
        when (installableWheels.size) {
            wheelNumber -> installableWheels.forEachIndexed { i, w ->
                wheels[CarWheelPlace.values()[i]] = w
            }
            else -> throw ServiceException("You have to install $wheelNumber wheels onto the car", HttpStatus.BAD_REQUEST)
        }
    }

}
