package com.example.goodscenter.ui.adapter

import android.content.Context
import com.example.baselibrary.ext.loadUrl
import com.example.goodscenter.R
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.goodscenter.data.protocol.Goods
import kotlinx.android.synthetic.main.layout_goods_item.view.*

class GoodsAdapter(context: Context): BaseRecyclerViewAdapter<Goods, BaseRecyclerViewAdapter.ViewHolder>(context) {

    override fun setView(): Int = R.layout.layout_goods_item

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        holder.itemView.mGoodsIconIv.loadUrl(model.goodsDefaultIcon)
        holder.itemView.mGoodsDescTv.text = model.goodsDesc
        holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(model.goodsDefaultPrice)
        holder.itemView.mGoodsSalesStockTv.text = "销量${model.goodsSalesCount}件     库存${model.goodsStockCount}"
    }

}