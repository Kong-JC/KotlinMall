package com.example.order.ui.activity

import com.example.baselibrary.ui.activity.BaseActivity
import com.example.order.R
import com.google.android.material.tabs.TabLayout
import com.kotlin.goodscenter.ui.adapter.OrderVpAdapter
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.common.OrderStatus
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : BaseActivity() {

    override fun setView(): Int = R.layout.activity_order

    override fun initView() {

        mOrderTab.tabMode = TabLayout.MODE_FIXED
        mOrderVp.adapter = OrderVpAdapter(supportFragmentManager)
        mOrderTab.setupWithViewPager(mOrderVp)

        mOrderVp.currentItem = intent.getIntExtra(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_ALL)

    }


}