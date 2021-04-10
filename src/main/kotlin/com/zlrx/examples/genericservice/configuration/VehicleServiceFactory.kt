package com.zlrx.examples.genericservice.configuration

import com.zlrx.examples.genericservice.domain.Vehicle
import com.zlrx.examples.genericservice.repository.VehicleRepository
import com.zlrx.examples.genericservice.service.VehicleService
import org.springframework.beans.factory.FactoryBean
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

interface VehicleServiceFactory {
    fun getService(type: String): VehicleService<out Vehicle, out VehicleRepository<out Vehicle>>
}

@Configuration
class VehicleServiceConfig {

    @Bean("vehicleServiceFactory")
    fun vehicleServiceFactoryLocator(): FactoryBean<Any> {
        val locator = ServiceLocatorFactoryBean()
        locator.setServiceLocatorInterface(VehicleServiceFactory::class.java)
        return locator
    }

}