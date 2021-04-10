package com.zlrx.examples.genericservice.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect


suspend fun <T> Flow<T>.toList(): List<T> {
    val list = mutableListOf<T>()
    this.collect {
        list.add(it)
    }
    return list;
}
