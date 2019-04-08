package com.example.goodscenter.service

import com.example.goodscenter.data.protocol.Category
import rx.Observable


interface CategoryService {
    fun getCategory(parentId:Int): Observable<MutableList<Category>?>
}