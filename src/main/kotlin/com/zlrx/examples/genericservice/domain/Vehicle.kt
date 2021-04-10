package com.zlrx.examples.genericservice.domain

import com.zlrx.examples.genericservice.error.ServiceException


abstract class Vehicle {
    abstract val wheelNumber: Int

    //wheels should be the part of the collection in case of document db,
    //but we demonstrate a connection between collections in this demo
    abstract var wheels: MutableList<Wheel>
    abstract val wheelIds: List<String>

    fun installWheels(installableWheels: List<Wheel>) {
        if (wheelIds.isNotEmpty()) {
            throw ServiceException("Wheels have already been installed")
        }
        addWheels(installableWheels)
    }

    protected abstract fun addWheels(installableWheels: List<Wheel>)
}