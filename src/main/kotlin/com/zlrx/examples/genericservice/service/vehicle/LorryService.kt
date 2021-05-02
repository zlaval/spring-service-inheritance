package com.zlrx.examples.genericservice.service.vehicle

import com.zlrx.examples.genericservice.domain.Lorry
import com.zlrx.examples.genericservice.repository.LorryRepository
import com.zlrx.examples.genericservice.utils.LORRY
import com.zlrx.examples.genericservice.utils.randomCharsThisLength
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service(value = LORRY)
class LorryService : AbstractVehicleService<Lorry, LorryRepository>() {

    private val rnd = Random(System.nanoTime())

    override val create = { producer: String, engineCapacity: Int ->
        Lorry(
            producer = producer,
            engineCapacity = engineCapacity,
            licencePlate = 6.randomCharsThisLength(),
            trailerSize = rnd.nextInt(2, 8),
            massTons = rnd.nextInt(10, 20)
        )
    }

}