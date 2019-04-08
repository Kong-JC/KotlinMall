package com.example.goodscenter.ui.activity

import com.example.baselibrary.ui.activity.BaseActivity
import com.example.goodscenter.R
import com.example.goodscenter.ui.fragment.CategoryFragment

class CategoryActivity : BaseActivity() {
    override fun setView(): Int = R.layout.activity_category

    override fun initView() {
        supportFragmentManager.beginTransaction().add(R.id.categoryFrame,CategoryFragment()).commit()
    }
}