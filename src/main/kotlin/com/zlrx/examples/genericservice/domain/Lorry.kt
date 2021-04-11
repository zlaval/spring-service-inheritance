package com.zlrx.examples.genericservice.domain

import com.zlrx.examples.genericservice.utils.LORRY

data class Lorry(
    var _id: String? = null,
    val producer: String,
    val licencePlate: String,
    val engineCapacity: Int,
) : Vehicle() {

    override var wheels: MutableList<Wheel> = mutableListOf()

    @org.springframework.data.annotation.Transient
    override val wheelNumber: Int = 12

    override fun getType() = LORRY

}
