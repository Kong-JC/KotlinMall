package com.example.order.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.order.presenter.view.OrderListView
import com.example.order.service.OrderService
import com.kotlin.order.data.protocol.Order
import javax.inject.Inject

class OrderListPresenter @Inject constructor() : BasePresenter<OrderListView>() {

    @Inject
    lateinit var orderService: OrderService

    fun getOrderList(orderStatus: Int) {
        if (!checkNetWork()) return
        mView.showLoading()
        orderService.getOrderList(orderStatus).excute(object : BaseSubscriber<MutableList<Order>?>(mView) {
            override fun onNext(t: MutableList<Order>?) {
                mView.onGetOrderListResult(t)
            }
        }, lifecycleProvider)
    }
    fun cancelOrder(orderId: Int) {
        if (!checkNetWork()) return
        mView.showLoading()
        orderService.cancelOrder(orderId).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onCancelOrderResult(t)
            }
        }, lifecycleProvider)
    }
    fun confirmOrder(orderId: Int) {
        if (!checkNetWork()) return
        mView.showLoading()
        orderService.confirmOrder(orderId).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onConfirmOrderResult(t)
            }
        }, lifecycleProvider)
    }

}