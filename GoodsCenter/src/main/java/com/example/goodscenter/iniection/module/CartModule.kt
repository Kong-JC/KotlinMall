package com.kotlin.goodscenter.injection.module

import com.example.goodscenter.service.CartService
import com.example.goodscenter.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides

/*
    购物车Module
 */
@Module
class CartModule {

    @Provides
    fun provideCartService(cartService: CartServiceImpl): CartService {
        return cartService
    }

}
