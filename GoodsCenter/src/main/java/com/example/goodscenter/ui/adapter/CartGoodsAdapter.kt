package com.kotlin.goodscenter.ui.adapter

import android.content.Context
import com.eightbitlab.rxbus.Bus
import com.example.baselibrary.ext.loadUrl
import com.example.baselibrary.ext.onClick
import com.example.goodscenter.R
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.base.widgets.DefaultTextWatcher
import com.kotlin.goodscenter.data.protocol.CartGoods
import com.kotlin.goodscenter.event.CartAllCheckedEvent
import com.kotlin.goodscenter.event.UpdateTotalPriceEvent
import com.kotlin.goodscenter.getEditText
import kotlinx.android.synthetic.main.layout_cart_goods_item.view.*

// 购物车数据适配器
class CartGoodsAdapter(context: Context) : BaseRecyclerViewAdapter<CartGoods, BaseRecyclerViewAdapter.ViewHolder>(context) {

    override fun setView(): Int =R.layout.layout_cart_goods_item
//    override fun setViewHolder(): ViewHolder = ViewHolder(mView)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        //是否选中
        holder.itemView.mCheckedCb.isChecked = model.isSelected
        //加载商品图片
        holder.itemView.mGoodsIconIv.loadUrl(model.goodsIcon)
        //商品描述
        holder.itemView.mGoodsDescTv.text = model.goodsDesc
        //商品SKU
        holder.itemView.mGoodsSkuTv.text = model.goodsSku
        //商品价格
        holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(model.goodsPrice)
        //商品数量
        holder.itemView.mGoodsCountBtn.setCurrentNumber(model.goodsCount)
        //选中按钮事件
        holder.itemView.mCheckedCb.onClick {
            model.isSelected = holder.itemView.mCheckedCb.isChecked
            val isAllChecked = dataList.all {it.isSelected }
            Bus.send(CartAllCheckedEvent(isAllChecked))
            notifyDataSetChanged()
        }

        //商品数量变化监听
        holder.itemView.mGoodsCountBtn.getEditText().addTextChangedListener(object:DefaultTextWatcher(){
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                model.goodsCount = s.toString().toInt()
                Bus.send(UpdateTotalPriceEvent())
            }
        })

    }

}
