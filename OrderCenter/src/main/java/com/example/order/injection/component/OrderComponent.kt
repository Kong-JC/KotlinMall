package com.example.order.injection.component

import com.example.baselibrary.injection.PresenterComponentScope
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.order.injection.module.OrderModule
import com.example.order.ui.activity.OrderConfirmActivity
import com.example.order.ui.activity.OrderDetailActivity
import com.example.order.ui.fragment.OrderFragment
import dagger.Component

@PresenterComponentScope
@Component(dependencies = [ActivityComponent::class], modules = [OrderModule::class])
interface OrderComponent {
    fun inject(activity: OrderConfirmActivity)
    fun inject(fragment: OrderFragment)
    fun inject(activity: OrderDetailActivity)
}