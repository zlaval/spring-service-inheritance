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

data class WheelInventory(
    var _id: String? = null,
    val size: Int,
    val tension: Int,
    var place: WheelPlace? = null
)