package com.zlrx.examples.genericservice.domain

enum class WheelPlace {
    FRONT_LEFT,
    FRONT_RIGHT,
    REAR_LEFT,
    REAR_RIGHT,
    MIDDLE_LEFT,
    MIDDLE_RIGHT,
    FRONT_LEFT_2,
    FRONT_RIGHT_2,
    REAR_LEFT_2,
    REAR_RIGHT_2,
    MIDDLE_LEFT_2,
    MIDDLE_RIGHT_2
}

val wheelGeneratorFn: (Int, String) -> List<Wheel> = { count, name ->
    (0 until count).map { index ->
        Wheel(size = 30, tension = 10, description = "$name wheel", place = getWheelPlace(index))
    }
}

fun getWheelPlace(index: Int): WheelPlace = WheelPlace.values()[index]

data class Wheel(
    val size: Int,
    val tension: Int,
    val description: String,
    val place: WheelPlace
)