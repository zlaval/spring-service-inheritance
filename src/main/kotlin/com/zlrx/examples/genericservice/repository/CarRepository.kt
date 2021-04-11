package com.zlrx.examples.genericservice.repository

import com.zlrx.examples.genericservice.domain.Car

interface CarRepository : VehicleRepository<Car>, CustomCarRepository {
}