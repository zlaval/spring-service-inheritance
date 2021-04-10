package com.zlrx.examples.genericservice.repository

import com.zlrx.examples.genericservice.domain.Wheel
import kotlinx.coroutines.flow.Flow
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.CrudRepository

interface WheelRepository : CrudRepository<Wheel, String> {

    @Query("{ mounted :{\$eq: null}, \$limit: ?0 }")
    suspend fun findNotInstalled(limit: Int): Flow<Wheel>

}