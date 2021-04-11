package com.zlrx.examples.genericservice.repository

import com.zlrx.examples.genericservice.domain.WheelInventory
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface WheelInventoryRepository : CoroutineCrudRepository<WheelInventory, String>