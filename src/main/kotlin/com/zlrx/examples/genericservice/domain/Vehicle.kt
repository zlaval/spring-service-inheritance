package com.zlrx.examples.genericservice.domain

interface Vehicle {
    val wheelNumber: Int
    val wheels: MutableMap<WheelPlace, Wheel>
    fun addWheels(installableWheels: List<Wheel>)
}