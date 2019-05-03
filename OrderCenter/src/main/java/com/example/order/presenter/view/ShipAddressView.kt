package com.example.order.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.kotlin.order.data.protocol.ShipAddress

interface ShipAddressView :BaseView{
    fun onGetShipAddressList(result:MutableList<ShipAddress>?)
    fun onDeleteShipAddress(result:Boolean)
    fun onEditShipAddress(result:Boolean)
}