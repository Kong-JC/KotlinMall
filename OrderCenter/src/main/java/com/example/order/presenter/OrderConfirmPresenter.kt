package com.example.order.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.order.presenter.view.OrderConfirmView
import com.example.order.service.OrderService
import com.kotlin.order.data.protocol.Order
import javax.inject.Inject

class OrderConfirmPresenter @Inject constructor() : BasePresenter<OrderConfirmView>() {

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
    fun submitOrder(order: Order) {
        if (!checkNetWork()) return
        mView.showLoading()
        orderService.submitOrder(order).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onSubmitOrderResult(t)
            }
        }, lifecycleProvider)
    }

}