package com.example.order.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.order.presenter.view.OrderDetailView
import com.example.order.service.OrderService
import com.kotlin.order.data.protocol.Order
import javax.inject.Inject

class OrderDetailPresenter @Inject constructor() : BasePresenter<OrderDetailView>() {

    @Inject
    lateinit var orderService: OrderService

    fun getOrderById(orderId: Int) {
        if (!checkNetWork()) return
        mView.showLoading()
        orderService.getOrderById(orderId).excute(object : BaseSubscriber<Order>(mView) {
            override fun onNext(t: Order) {
                mView.onGetOrderByIdResult(t)
            }
        }, lifecycleProvider)
    }

}