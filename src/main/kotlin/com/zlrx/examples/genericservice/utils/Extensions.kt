package com.zlrx.examples.genericservice.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import org.apache.commons.lang3.RandomStringUtils
import java.util.Optional


suspend fun <T> Flow<T>.toList(): MutableList<T> {
    val list = mutableListOf<T>()
    this.collect {
        list.add(it)
    }
    return list;
}

fun Int.randomCharsThisLength(): String = RandomStringUtils.randomAlphanumeric(this)

fun Optional<String>.toInt() = if (this.isPresent) this.get().toInt() else 0