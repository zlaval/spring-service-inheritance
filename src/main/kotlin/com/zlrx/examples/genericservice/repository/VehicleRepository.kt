package com.zlrx.examples.genericservice.repository

import com.zlrx.examples.genericservice.domain.Vehicle
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

@NoRepositoryBean
interface VehicleRepository<T : Vehicle> : CoroutineCrudRepository<T, String> {
}