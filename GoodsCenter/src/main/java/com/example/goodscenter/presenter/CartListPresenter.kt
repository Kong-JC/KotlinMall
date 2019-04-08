package com.kotlin.goodscenter.presenter

import com.example.baselibrary.ext.execute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.goodscenter.service.CartService
import com.kotlin.goodscenter.data.protocol.CartGoods
import com.kotlin.goodscenter.presenter.view.CartListView
import javax.inject.Inject

// 购物车 Presenter
class CartListPresenter @Inject constructor() : BasePresenter<CartListView>() {

    @Inject
    lateinit var cartService: CartService

    // 获取购物车列表
    fun getCartList() {
        if (!checkNetWork()) return
        mView.showLoading()
        cartService.getCartList().execute(object : BaseSubscriber<MutableList<CartGoods>?>(mView) {
            override fun onNext(t: MutableList<CartGoods>?) {
                mView.onGetCartListResult(t)
            }
        }, lifecycleProvider)
    }

    // 删除购物车商品
    fun deleteCartList(list: List<Int>) {
        if (!checkNetWork()) return
        mView.showLoading()
        cartService.deleteCartList(list).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onDeleteCartListResult(t)
            }
        }, lifecycleProvider)
    }

    // 提交购物车商品
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long) {
        if (!checkNetWork()) return
        mView.showLoading()
        cartService.submitCart(list, totalPrice).execute(object : BaseSubscriber<Int>(mView) {
            override fun onNext(t: Int) {
                mView.onSubmitCartListResult(t)
            }
        }, lifecycleProvider)
    }

}
