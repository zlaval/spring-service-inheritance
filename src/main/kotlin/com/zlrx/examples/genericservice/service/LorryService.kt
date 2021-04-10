package com.zlrx.examples.genericservice.service

import com.zlrx.examples.genericservice.domain.Lorry
import com.zlrx.examples.genericservice.repository.LorryRepository
import com.zlrx.examples.genericservice.utils.randomCharsThisLength
import org.springframework.stereotype.Service

@Service(value = VehicleType.LORRY)
class LorryService : VehicleService<Lorry, LorryRepository>() {

    override val create = { producer: String, engineCapacity: Int ->
        Lorry(producer = producer, engineCapacity = engineCapacity, licencePlate = 6.randomCharsThisLength())
    }

}