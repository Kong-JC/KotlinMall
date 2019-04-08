package com.example.goodscenter.ui.activity

import com.example.baselibrary.ui.activity.BaseActivity
import com.example.goodscenter.R
import com.kotlin.goodscenter.ui.fragment.CartFragment

/*
    购物车Activity
    只是Fragment一个壳
 */
class CartActivity: BaseActivity() {
    override fun setView(): Int = R.layout.activity_cart

    override fun initView() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_cart)
        (fragment as CartFragment).setBackVisible(true)
    }

}
