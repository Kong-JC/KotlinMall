package com.example.goodscenter.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.example.goodscenter.data.protocol.Category

interface CategoryView:BaseView {
    fun onCategoryResult(result:MutableList<Category>?)
}