package com.example.goodscenter.data.repository

import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.data.protocol.BaseResponse
import com.example.goodscenter.data.api.CategoryApi
import com.example.goodscenter.data.protocol.Category
import com.example.goodscenter.data.protocol.GetCategoryRequest
import rx.Observable
import javax.inject.Inject

class CategoryRepository @Inject constructor() {

    fun getCategory(parentId: Int): Observable<BaseResponse<MutableList<Category>?>> {
        return RetrofitFactory.instance.create(CategoryApi::class.java)
                .getCategory(GetCategoryRequest(parentId))
    }

}