package com.example.goodscenter.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.kotlin.goodscenter.data.protocol.Goods

interface GoodsListView : BaseView {
    fun onGoodsListResult(result: MutableList<Goods>?)
//    fun onGoodsListByKeyword(result: MutableList<Goods>?)
//    fun onGoodsDetail(result: Goods)
}