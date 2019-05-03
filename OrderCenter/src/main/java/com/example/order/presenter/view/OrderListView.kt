package com.example.order.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.kotlin.order.data.protocol.Order

interface OrderListView :BaseView{
    fun onGetOrderListResult(result:MutableList<Order>?)
    fun onCancelOrderResult(result:Boolean)
    fun onConfirmOrderResult(result:Boolean)
}