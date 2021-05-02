package com.zlrx.examples.genericservice.domain

sealed class Vehicle {
    abstract val wheelNumber: Int
}

val installLorryWheels: suspend (Lorry) -> Lorry = {
    val wheels = wheelGeneratorFn(it.wheelNumber, "Lorry")
    it.copy(
        wheels = wheels,
        massTons = it.massTons + 1
    )
}

data class Lorry(
    val _id: String? = null,
    val producer: String,
    val licencePlate: String,
    val engineCapacity: Int,
    val wheels: List<Wheel> = emptyList(),
    val trailerSize: Int,
    val massTons: Int = 10
) : Vehicle() {

    @Transient
    override val wheelNumber: Int = 12

}

val installCarWheels: (Car, (Int, String) -> List<Wheel>) -> Car = { car, generator ->
    val wheels = generator(car.wheelNumber, "Bus")
    car.copy(
        wheels = wheels
    )
}


data class Car(
    val _id: String? = null,
    val producer: String,
    val licencePlate: String,
    val engineCapacity: Int,
    val wheels: List<Wheel> = emptyList()
) : Vehicle() {

    @Transient
    override val wheelNumber: Int = 4

}

val installBusWheels: (Bus, (Int, String) -> List<Wheel>) -> Bus = { bus, generator ->
    val wheels = generator(bus.wheelNumber, "Bus")
    bus.copy(
        wheels = wheels
    )
}

data class Bus(
    val _id: String? = null,
    val producer: String,
    val licencePlate: String,
    val engineCapacity: Int,
    val wheels: List<Wheel> = emptyList(),
    val passengerCount: Int
) : Vehicle() {

    @Transient
    override val wheelNumber: Int = 6

}



