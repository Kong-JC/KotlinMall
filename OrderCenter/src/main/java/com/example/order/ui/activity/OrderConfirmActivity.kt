package com.example.order.ui.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.order.R
import com.kotlin.provider.router.RouterPath

@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity :BaseActivity() {

    override fun setView(): Int = R.layout.activity_order_confirm

    override fun initView() {

    }

}