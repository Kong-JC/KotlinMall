package com.example.goodscenter.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.goodscenter.data.protocol.Category
import com.example.goodscenter.presenter.view.CategoryView
import com.example.goodscenter.service.CategoryService
import javax.inject.Inject

class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {

    @Inject
    lateinit var categoryService: CategoryService

    fun getCategory(parentId: Int) {
        if (!checkNetWork()) return
        mView.showLoading()
        categoryService.getCategory(parentId).excute(object : BaseSubscriber<MutableList<Category>?>(mView) {
            override fun onNext(t: MutableList<Category>?) {
                mView.onCategoryResult(t)
            }
        }, lifecycleProvider)
    }

}