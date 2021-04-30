package com.zlrx.examples.genericservice.repository

import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

@NoRepositoryBean
interface VehicleRepository<T : Vehicle> : CoroutineCrudRepository<T, String> {
}