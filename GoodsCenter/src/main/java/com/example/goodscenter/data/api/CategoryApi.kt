package com.example.goodscenter.data.api

import com.example.baselibrary.data.protocol.BaseResponse
import com.example.goodscenter.data.protocol.Category
import com.example.goodscenter.data.protocol.GetCategoryRequest
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface CategoryApi {
    // 获取商品分类列表
    @POST("category/getCategory")
    fun getCategory(@Body req: GetCategoryRequest):Observable<BaseResponse<MutableList<Category>?>>
}