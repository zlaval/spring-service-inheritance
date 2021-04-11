package com.zlrx.examples.genericservice.service


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

//Example generic service implements crud operations
abstract class CrudService<T, RT : CoroutineCrudRepository<T, String>> {

    @Autowired
    protected lateinit var repository: RT

    fun findAll() = repository.findAll()

    suspend fun save(entity: T): T = repository.save(entity)

    suspend fun delete(entity: T) = repository.delete(entity)

    //[other operations here]

}