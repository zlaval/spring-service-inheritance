package com.zlrx.examples.genericservice.service

import com.zlrx.examples.genericservice.domain.Car
import com.zlrx.examples.genericservice.repository.CarRepository
import com.zlrx.examples.genericservice.utils.randomCharsThisLength
import org.springframework.stereotype.Service

@Service(value = VehicleType.CAR)
class CarService : VehicleService<Car, CarRepository>() {

    override val create = { producer: String, engineCapacity: Int ->
        Car(producer = producer, engineCapacity = engineCapacity, licencePlate = 6.randomCharsThisLength())
    }

}