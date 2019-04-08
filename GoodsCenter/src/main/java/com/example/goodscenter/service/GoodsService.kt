package com.example.goodscenter.service

import com.kotlin.goodscenter.data.protocol.Goods
import rx.Observable

interface GoodsService {
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?>
    fun getGoodsDetail(goodsId: Int): Observable<Goods>
}