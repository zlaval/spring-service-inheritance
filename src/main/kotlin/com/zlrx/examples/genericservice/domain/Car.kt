package com.zlrx.examples.genericservice.domain

import com.zlrx.examples.genericservice.utils.CAR

data class Car(
    var _id: String? = null,
    val producer: String,
    val licencePlate: String,
    val engineCapacity: Int,
) : Vehicle() {

    override var wheels: MutableList<Wheel> = mutableListOf()

    @org.springframework.data.annotation.Transient
    override val wheelNumber: Int = 4

    override fun getType() = CAR
}
