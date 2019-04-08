package com.example.goodscenter.ui.adapter

import android.content.Context
import com.example.baselibrary.ext.loadUrl
import com.example.goodscenter.R
import com.example.goodscenter.data.protocol.Category
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_second_category_item.view.*

class SecondCategoryAdapter(context: Context) : BaseRecyclerViewAdapter<Category, BaseRecyclerViewAdapter.ViewHolder>(context) {

    override fun setView(): Int = R.layout.layout_second_category_item

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        holder.itemView.mSecondCategoryNameTv.text = model.categoryName
        holder.itemView.mSecondCategoryNameTv.isSelected = model.isSelected
        holder.itemView.mCategoryIconIv.loadUrl(model.categoryIcon)
        holder.itemView.mSecondCategoryNameTv.text = model.categoryName
    }

}