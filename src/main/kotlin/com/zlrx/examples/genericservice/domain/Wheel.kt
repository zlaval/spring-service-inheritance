package com.zlrx.examples.genericservice.domain

interface WheelPlace

enum class CarWheelPlace : WheelPlace {
    FRONT_LEFT,
    FRONT_RIGHT,
    REAR_LEFT,
    REAR_RIGHT
}

enum class BusWheelPlace : WheelPlace {
    FRONT_LEFT,
    FRONT_RIGHT,
    REAR_LEFT,
    REAR_RIGHT,
    MIDDLE_LEFT,
    MIDDLE_RIGHT
}

enum class LorryWheelPlace : WheelPlace {
    FRONT_LEFT_1,
    FRONT_LEFT_2,
    FRONT_RIGHT_1,
    FRONT_RIGHT_2,
    REAR_LEFT_1,
    REAR_LEFT_2,
    REAR_RIGHT_1,
    REAR_RIGHT_2,
    MIDDLE_LEFT_1,
    MIDDLE_LEFT_2,
    MIDDLE_RIGHT_1,
    MIDDLE_RIGHT_2
}

data class Wheel(
    var _id: String? = null,
    val size: Int,
    val tension: Int,
    val producer: String,
    var mounted: String? = null
)