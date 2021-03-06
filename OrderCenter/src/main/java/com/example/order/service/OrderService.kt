package com.example.order.service

import com.kotlin.order.data.protocol.Order
import rx.Observable

interface OrderService {
    // 根据ID查询订单
    fun getOrderById(orderId: Int): Observable<Order>
    // 提交订单
    fun submitOrder(order: Order): Observable<Boolean>
    // 取消订单
    fun cancelOrder(orderId: Int): Observable<Boolean>
    // 确认订单
    fun confirmOrder(orderId: Int): Observable<Boolean>
    // 根据状态查询订单列表
    fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?>

}