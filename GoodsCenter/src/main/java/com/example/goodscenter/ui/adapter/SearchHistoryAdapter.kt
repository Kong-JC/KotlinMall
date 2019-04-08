package com.kotlin.goodscenter.ui.adapter

import android.content.Context
import com.example.goodscenter.R
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_search_history_item.view.*

/*
    搜索历史记录
 */
class SearchHistoryAdapter(context: Context) : BaseRecyclerViewAdapter<String, BaseRecyclerViewAdapter.ViewHolder>(context) {
    override fun setView(): Int = R.layout.layout_search_history_item
//    override fun setViewHolder(): ViewHolder = ViewHolder(mView)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        holder.itemView.mSearchHistoryTv.text = model
    }

}
