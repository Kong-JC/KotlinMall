package com.example.goodscenter.ui.adapter

import android.content.Context
import com.example.goodscenter.R
import com.example.goodscenter.data.protocol.Category
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_top_category_item.view.*

class TopCategoryAdapter(context: Context) : BaseRecyclerViewAdapter<Category, BaseRecyclerViewAdapter.ViewHolder>(context) {

    override fun setView(): Int = R.layout.layout_top_category_item
//    override fun setViewHolder(): ViewHolder = ViewHolder(mView)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        holder.itemView.mTopCategoryNameTv.text = model.categoryName
        holder.itemView.mTopCategoryNameTv.isSelected = model.isSelected
    }

//    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

}