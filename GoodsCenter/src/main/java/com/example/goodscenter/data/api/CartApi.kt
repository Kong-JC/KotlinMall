package com.kotlin.goodscenter.data.api

import com.example.baselibrary.data.protocol.BaseResponse
import com.kotlin.goodscenter.data.protocol.AddCartReq
import com.kotlin.goodscenter.data.protocol.CartGoods
import com.kotlin.goodscenter.data.protocol.DeleteCartReq
import com.kotlin.goodscenter.data.protocol.SubmitCartReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
    购物车 接口
 */
interface CartApi {
    /*
        获取购物车列表
     */
    @POST("cart/getList")
    fun getCartList(): Observable<BaseResponse<MutableList<CartGoods>?>>

    /*
        添加商品到购物车
     */
    @POST("cart/add")
    fun addCart(@Body req: AddCartReq): Observable<BaseResponse<Int>>

    /*
        删除购物车商品
     */
    @POST("cart/delete")
    fun deleteCartList(@Body req: DeleteCartReq): Observable<BaseResponse<String>>

    /*
        提交购物车商品
     */
    @POST("cart/submit")
    fun submitCart(@Body req: SubmitCartReq): Observable<BaseResponse<Int>>
}
