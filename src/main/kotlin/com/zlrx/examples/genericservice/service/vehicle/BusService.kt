package com.zlrx.examples.genericservice.service.vehicle

import com.zlrx.examples.genericservice.domain.Bus
import com.zlrx.examples.genericservice.repository.BusRepository
import com.zlrx.examples.genericservice.utils.BUS
import com.zlrx.examples.genericservice.utils.randomCharsThisLength
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service(value = BUS)
class BusService : AbstractVehicleService<Bus, BusRepository>() {

    override val create = { producer: String, engineCapacity: Int ->
        Bus(
            producer = producer,
            engineCapacity = engineCapacity,
            licencePlate = 6.randomCharsThisLength(),
            passengerCount = Random(System.nanoTime()).nextInt(20, 40)
        )
    }

}