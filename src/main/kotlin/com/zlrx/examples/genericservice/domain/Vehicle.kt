package com.zlrx.examples.genericservice.domain

sealed class Vehicle {
    abstract val wheelNumber: Int
}

data class Lorry(
    var _id: String? = null,
    val producer: String,
    val licencePlate: String,
    val engineCapacity: Int,
    val wheels: List<WheelInventory> = emptyList(),
    val trailerSize: Int,
    val massTons: Int = 10
) : Vehicle() {

    @Transient
    override val wheelNumber: Int = 12

}

data class Car(
    var _id: String? = null,
    val producer: String,
    val licencePlate: String,
    val engineCapacity: Int,
    val wheels: List<WheelInventory> = emptyList()
) : Vehicle() {

    @Transient
    override val wheelNumber: Int = 4

}

data class Bus(
    var _id: String? = null,
    val producer: String,
    val licencePlate: String,
    val engineCapacity: Int,
    val wheels: List<WheelInventory> = emptyList(),
    val passengerCount: Int
) : Vehicle() {

    @Transient
    override val wheelNumber: Int = 6

}



