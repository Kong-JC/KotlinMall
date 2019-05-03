package com.example.goodscenter.iniection.component

import com.example.baselibrary.injection.PresenterComponentScope
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.goodscenter.iniection.module.GoodsModule
import com.example.goodscenter.ui.activity.GoodsActivity
import com.kotlin.goodscenter.injection.module.CartModule
import com.kotlin.goodscenter.ui.fragment.GoodsDetailTabOneFragment
import dagger.Component

@PresenterComponentScope
@Component(dependencies = [ActivityComponent::class], modules = [GoodsModule::class, CartModule::class])
interface GoodsComponent {
    fun inject(activity: GoodsActivity)
    fun inject(fragment: GoodsDetailTabOneFragment)
}