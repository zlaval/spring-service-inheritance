package com.zlrx.examples.genericservice.domain

import com.zlrx.examples.genericservice.utils.LORRY

data class Lorry(
    var _id: String? = null,
    val producer: String,
    val licencePlate: String,
    val engineCapacity: Int,
    override val wheelIds: MutableList<String> = mutableListOf()
) : Vehicle() {

    @org.springframework.data.annotation.Transient
    override var wheels = mutableListOf<Wheel>()

    @org.springframework.data.annotation.Transient
    override val wheelNumber: Int = 12

    override fun getType() = LORRY

}
