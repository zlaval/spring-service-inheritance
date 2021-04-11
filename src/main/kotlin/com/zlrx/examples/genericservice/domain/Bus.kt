package com.zlrx.examples.genericservice.domain

import com.zlrx.examples.genericservice.utils.BUS

data class Bus(
    var _id: String? = null,
    val producer: String,
    val licencePlate: String,
    override var engineCapacity: Int,
    override var wheels: MutableList<WheelInventory> = mutableListOf()
) : Vehicle() {

    @org.springframework.data.annotation.Transient
    override val wheelNumber: Int = 6

    override fun getType(): String = BUS

}
