package com.kotlin.order.ui.adapter

import android.content.Context
import com.example.baselibrary.ext.onClick
import com.example.order.R
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.order.data.protocol.ShipAddress
import kotlinx.android.synthetic.main.layout_address_item.view.*

/*
    收货地址数据适配
 */
class ShipAddressAdapter(context: Context) : BaseRecyclerViewAdapter<ShipAddress, BaseRecyclerViewAdapter.ViewHolder>(context) {
    override fun setView(): Int = R.layout.layout_address_item

    var mOptClickListener: OnOptClickListener? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        holder.itemView.mSetDefaultTv.isSelected = (model.shipIsDefault == 0)
        holder.itemView.mShipNameTv.text = model.shipUserName + "    " + model.shipUserMobile
        holder.itemView.mShipAddressTv.text = model.shipAddress

        holder.itemView.mSetDefaultTv.onClick {
            mOptClickListener?.let {
                if (holder.itemView.mSetDefaultTv.isSelected) return@onClick
                model.shipIsDefault = 0
                it.onSetDefault(model)
            }
        }

        holder.itemView.mEditTv.onClick {
            mOptClickListener?.let {
                it.onEdit(model)
            }
        }
        holder.itemView.mDeleteTv.onClick {
            mOptClickListener?.let {
                it.onDelete(model)
            }
        }

    }

    /*
        对应操作接口
     */
    interface OnOptClickListener {
        fun onSetDefault(address: ShipAddress)
        fun onEdit(address: ShipAddress)
        fun onDelete(address: ShipAddress)
    }
}
