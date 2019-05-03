package com.example.order.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.kotlin.order.data.protocol.Order

interface OrderConfirmView :BaseView{
    fun onGetOrderByIdResult(result:Order)
    fun onSubmitOrderResult(result:Boolean)
}