package com.zlrx.examples.genericservice.service

import com.zlrx.examples.genericservice.domain.Bus
import com.zlrx.examples.genericservice.repository.BusRepository
import com.zlrx.examples.genericservice.utils.BUS
import com.zlrx.examples.genericservice.utils.randomCharsThisLength
import org.springframework.stereotype.Service

@Service(value = BUS)
class BusService : VehicleService<Bus, BusRepository>() {

    override val create = { producer: String, engineCapacity: Int ->
        Bus(producer = producer, engineCapacity = engineCapacity, licencePlate = 6.randomCharsThisLength())
    }

}