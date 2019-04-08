package com.kotlin.goodscenter.data.api

import com.example.baselibrary.data.protocol.BaseResponse
import com.kotlin.goodscenter.data.protocol.GetGoodsDetailReq
import com.kotlin.goodscenter.data.protocol.GetGoodsListByKeywordReq
import com.kotlin.goodscenter.data.protocol.GetGoodsListReq
import com.kotlin.goodscenter.data.protocol.Goods
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
    商品接口
 */
interface GoodsApi {
    /*
        获取商品列表
     */
    @POST("goods/getGoodsList")
    fun getGoodsList(@Body req: GetGoodsListReq): Observable<BaseResponse<MutableList<Goods>?>>

    /*
        获取商品列表
     */
    @POST("goods/getGoodsListByKeyword")
    fun getGoodsListByKeyword(@Body req: GetGoodsListByKeywordReq): Observable<BaseResponse<MutableList<Goods>?>>

    /*
        获取商品详情
     */
    @POST("goods/getGoodsDetail")
    fun getGoodsDetail(@Body req: GetGoodsDetailReq): Observable<BaseResponse<Goods>>
}
