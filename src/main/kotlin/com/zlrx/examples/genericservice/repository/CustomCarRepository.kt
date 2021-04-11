package com.zlrx.examples.genericservice.repository

import com.zlrx.examples.genericservice.domain.Car
import kotlinx.coroutines.flow.Flow

interface CustomCarRepository {

    fun findByEngineCapacityGte(minEngineCapacity: Int): Flow<Car>
}