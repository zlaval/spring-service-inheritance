package com.zlrx.examples.genericservice.domain

import com.zlrx.examples.genericservice.utils.CAR

data class Car(
    var _id: String? = null,
    val producer: String,
    val licencePlate: String,
    override var engineCapacity: Int,
    override var wheels: MutableList<WheelInventory> = mutableListOf()
) : Vehicle() {

    @org.springframework.data.annotation.Transient
    override val wheelNumber: Int = 4

    override fun getType() = CAR
}
