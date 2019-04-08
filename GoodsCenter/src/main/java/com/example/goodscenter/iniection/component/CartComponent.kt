package com.kotlin.goodscenter.injection.component

import com.example.baselibrary.injection.PresenterComponentScope
import com.example.baselibrary.injection.component.ActivityComponent
import com.kotlin.goodscenter.injection.module.CartModule
import com.kotlin.goodscenter.ui.fragment.CartFragment
import dagger.Component

/*
    购物车Component
 */
@PresenterComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(CartModule::class))
interface CartComponent {
    fun inject(fragment: CartFragment)
}
