package com.zlrx.examples.genericservice.repository

import com.zlrx.examples.genericservice.domain.Car
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.gte
import org.springframework.stereotype.Repository

@Repository
class CustomCarRepositoryImpl(
    private val mongo: ReactiveMongoTemplate
) : CustomCarRepository {

    override fun findByEngineCapacityGte(minEngineCapacity: Int): Flow<Car> {
        val criteria = Car::engineCapacity gte minEngineCapacity
        return mongo.find(Query(criteria), Car::class.java).asFlow()
    }

}