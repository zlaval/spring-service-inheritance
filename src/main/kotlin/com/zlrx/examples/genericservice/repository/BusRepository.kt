package com.zlrx.examples.genericservice.repository

import com.zlrx.examples.genericservice.domain.Bus

interface BusRepository : VehicleRepository<Bus> {
}