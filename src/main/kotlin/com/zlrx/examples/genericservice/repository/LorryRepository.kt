package com.zlrx.examples.genericservice.repository

import com.zlrx.examples.genericservice.domain.Lorry

interface LorryRepository : VehicleRepository<Lorry> {
}