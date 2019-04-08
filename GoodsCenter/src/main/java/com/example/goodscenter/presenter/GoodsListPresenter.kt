package com.example.goodscenter.presenter

import com.example.baselibrary.ext.execute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.goodscenter.presenter.view.GoodsListView
import com.example.goodscenter.service.GoodsService
import com.kotlin.goodscenter.data.protocol.Goods
import javax.inject.Inject

class GoodsListPresenter @Inject constructor() : BasePresenter<GoodsListView>() {

    @Inject
    lateinit var goodsService: GoodsService

    fun getGoodsList(categoryId: Int, pageNo: Int) {
        if (!checkNetWork()) return
        mView.showLoading()
        goodsService.getGoodsList(categoryId,pageNo).execute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
            override fun onNext(t: MutableList<Goods>?) {
                mView.onGoodsListResult(t)
            }
        }, lifecycleProvider)
    }

    fun getGoodsListByKeyword(keyword: String, pageNo: Int) {
        if (!checkNetWork()) return
        mView.showLoading()
        goodsService.getGoodsListByKeyword(keyword,pageNo).execute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
            override fun onNext(t: MutableList<Goods>?) {
//                mView.onGoodsListByKeyword(t)
                mView.onGoodsListResult(t)
            }
        }, lifecycleProvider)
    }

    fun getGoodsDetail(goodsId: Int) {
        if (!checkNetWork()) return
        mView.showLoading()
        goodsService.getGoodsDetail(goodsId).execute(object : BaseSubscriber<Goods>(mView) {
            override fun onNext(t: Goods) {
//                mView.onGoodsDetail(t)
            }
        }, lifecycleProvider)
    }

}