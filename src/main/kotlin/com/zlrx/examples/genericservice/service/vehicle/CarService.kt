package com.zlrx.examples.genericservice.service.vehicle

import com.zlrx.examples.genericservice.domain.Car
import com.zlrx.examples.genericservice.repository.CarRepository
import com.zlrx.examples.genericservice.utils.CAR
import com.zlrx.examples.genericservice.utils.randomCharsThisLength
import org.springframework.stereotype.Service

@Service(value = CAR)
class CarService : AbstractVehicleService<Car, CarRepository>() {

    override val create = { producer: String, engineCapacity: Int ->
        Car(producer = producer, engineCapacity = engineCapacity, licencePlate = 6.randomCharsThisLength())
    }

}