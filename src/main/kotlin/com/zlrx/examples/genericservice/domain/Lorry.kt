package com.zlrx.examples.genericservice.domain

import com.zlrx.examples.genericservice.utils.LORRY

data class Lorry(
    var _id: String? = null,
    val producer: String,
    val licencePlate: String,
    val engineCapacity: Int,
    override var wheels: MutableList<WheelInventory> = mutableListOf()
) : Vehicle() {

    @org.springframework.data.annotation.Transient
    override val wheelNumber: Int = 12

    override fun getType() = LORRY

}
