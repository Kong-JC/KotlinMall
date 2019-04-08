package com.example.goodscenter.service.impl

import com.example.baselibrary.ext.convert
import com.example.goodscenter.data.protocol.Category
import com.example.goodscenter.data.repository.CategoryRepository
import com.example.goodscenter.service.CategoryService
import rx.Observable
import javax.inject.Inject

class CategoryServiceImpl @Inject constructor() : CategoryService {

    @Inject
    lateinit var repository: CategoryRepository

    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> {
        return repository.getCategory(parentId).convert()
    }

}