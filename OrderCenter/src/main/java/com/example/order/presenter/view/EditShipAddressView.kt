package com.example.order.presenter.view

import com.example.baselibrary.presenter.view.BaseView

interface EditShipAddressView :BaseView{
    fun onAddShipAddressResult(result:Boolean)
    fun onEditShipAddressResult(result:Boolean)
}