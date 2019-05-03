package com.example.order.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.kotlin.order.data.protocol.Order

interface OrderDetailView :BaseView{
    fun onGetOrderByIdResult(result:Order)
}