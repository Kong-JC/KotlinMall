package com.example.order.injection.module

import com.example.order.service.ShipAddressService
import com.example.order.service.impl.ShipAddressServiceImpl
import dagger.Module
import dagger.Provides

@Module
class ShipAddressModule {

    @Provides
    fun providesShipAddressService(service: ShipAddressServiceImpl): ShipAddressService = service

}