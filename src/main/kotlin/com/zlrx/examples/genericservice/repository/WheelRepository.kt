package com.zlrx.examples.genericservice.repository

import com.zlrx.examples.genericservice.domain.Wheel
import kotlinx.coroutines.flow.Flow
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface WheelRepository : CoroutineCrudRepository<Wheel, String> {

    @Query("{ mounted :{\$eq: false}}")
    suspend fun findNotInstalled(): Flow<Wheel>

    @Query("{_id: { \$in: ?0} }")
    suspend fun findByIdIn(ids: List<String>): Flow<Wheel>

}