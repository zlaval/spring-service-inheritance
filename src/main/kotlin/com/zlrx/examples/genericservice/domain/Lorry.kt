package com.zlrx.examples.genericservice.domain

import com.zlrx.examples.genericservice.error.ServiceException
import org.springframework.http.HttpStatus

data class Lorry(
    var _id: String? = null,
    val producer: String,
    val licencePlate: String,
    val engineCapacity: Int,
    override val wheelIds: MutableList<String> = mutableListOf()
) : Vehicle() {

    //@DBRef can be used here in non reactive environment
    @org.springframework.data.annotation.Transient
    override var wheels = mutableListOf<Wheel>()

    @org.springframework.data.annotation.Transient
    override val wheelNumber: Int = 12

    //treat as a custom logic in demo (can be extracted into superclass)
    override fun addWheels(installableWheels: List<Wheel>) {
        when (installableWheels.size) {
            wheelNumber -> installableWheels.forEachIndexed { i, w ->
                val place = WheelPlace.values()[i]
                w.place = place
                w.mounted = true
                w.description = "installed on lorry"
                wheels.add(w)
                wheelIds.add(w._id!!)
            }
            else -> throw ServiceException("You have to install $wheelNumber wheels onto the car", HttpStatus.BAD_REQUEST)
        }
    }


}
