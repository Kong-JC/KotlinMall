package com.example.goodscenter.iniection.module

import com.example.goodscenter.service.GoodsService
import com.example.goodscenter.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

@Module
class GoodsModule {

    @Provides
    fun providesGoodsService(service: GoodsServiceImpl): GoodsService = service

}