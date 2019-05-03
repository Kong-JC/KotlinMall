package com.example.order.injection.component

import com.example.baselibrary.injection.PresenterComponentScope
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.order.injection.module.ShipAddressModule
import com.example.order.ui.activity.ShipAddressActivity
import com.example.order.ui.activity.ShipAddressEditActivity
import dagger.Component

@PresenterComponentScope
@Component(dependencies = [ActivityComponent::class], modules = [ShipAddressModule::class])
interface ShipAddressComponent {
    fun inject(activity: ShipAddressEditActivity)
    fun inject(activity: ShipAddressActivity)
}