package com.example.order.injection.module

import com.example.order.service.OrderService
import com.example.order.service.impl.OrderServiceImpl
import dagger.Module
import dagger.Provides

@Module
class OrderModule {

    @Provides
    fun providesOrderService(service: OrderServiceImpl): OrderService = service

}