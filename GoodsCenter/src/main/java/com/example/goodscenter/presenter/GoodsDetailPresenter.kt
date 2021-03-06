package com.kotlin.goodscenter.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.goodscenter.service.CartService
import com.example.goodscenter.service.GoodsService
import com.kotlin.goodscenter.data.protocol.Goods
import com.kotlin.goodscenter.presenter.view.GoodsDetailView
import javax.inject.Inject

/*
    商品详情 Presenter
 */
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var goodsService: GoodsService

    @Inject
    lateinit var cartService: CartService

    // 获取商品详情
    fun getGoodsDetailList(goodsId: Int) {
        if (!checkNetWork()) return
        mView.showLoading()
        goodsService.getGoodsDetail(goodsId).excute(object : BaseSubscriber<Goods>(mView) {
            override fun onNext(t: Goods) {
                mView.onGetGoodsDetailResult(t)
            }
        }, lifecycleProvider)
    }

    // 获取商品详情
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String) {
        if (!checkNetWork()) return
        mView.showLoading()
        cartService.addCart(goodsId, goodsDesc, goodsIcon, goodsPrice,
                goodsCount, goodsSku).excute(object : BaseSubscriber<Int>(mView) {
            override fun onNext(t: Int) {
                mView.onAddCartResult(t)
            }
        }, lifecycleProvider)
    }

}
