package com.zlrx.examples.genericservice.repository

import com.zlrx.examples.genericservice.domain.WheelInventory
import kotlinx.coroutines.flow.Flow
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface WheelInventoryRepository : CoroutineCrudRepository<WheelInventory, String> {

    @Query("{_id: { \$in: ?0} }")
    suspend fun findByIdIn(ids: List<String>): Flow<WheelInventory>

}