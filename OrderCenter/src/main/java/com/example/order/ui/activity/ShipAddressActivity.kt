package com.example.order.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.eightbitlab.rxbus.Bus
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ext.startLoading
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.order.R
import com.example.order.event.SelectAddressEvent
import com.example.order.injection.component.DaggerShipAddressComponent
import com.example.order.injection.module.ShipAddressModule
import com.example.order.presenter.ShipAddressPresenter
import com.example.order.presenter.view.ShipAddressView
import com.kennyc.view.MultiStateView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.data.protocol.ShipAddress
import com.kotlin.order.ui.adapter.ShipAddressAdapter
import kotlinx.android.synthetic.main.activity_address.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ShipAddressActivity : BaseMvpActivity<ShipAddressPresenter>(), ShipAddressView {

    override fun setView(): Int = R.layout.activity_address
    lateinit var mAdapter: ShipAddressAdapter

    override fun injectComponent() {
        DaggerShipAddressComponent.builder().activityComponent(activityComponent)
                .shipAddressModule(ShipAddressModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun initView() {
        // super.initView()
        mAdapter = ShipAddressAdapter(this)
        mAddressRv.layoutManager = LinearLayoutManager(this)
        mAddressRv.adapter = mAdapter

        mAdapter.mOptClickListener = object : ShipAddressAdapter.OnOptClickListener {
            override fun onSetDefault(address: ShipAddress) {
                mPresenter.editShipAddress(address)
            }

            override fun onEdit(address: ShipAddress) {
                startActivity<ShipAddressEditActivity>(
                        OrderConstant.KEY_SHIP_ADDRESS to address
                )
            }

            override fun onDelete(address: ShipAddress) {
                AlertView("删除", "是否删除该地址？", "否", null, arrayOf("是"), this@ShipAddressActivity,
                        AlertView.Style.Alert, OnItemClickListener { o, position ->
                    if (position == 0) mPresenter.deleteShipAddress(address.id)
                }).show()
            }
        }
        mAdapter.setOnItemClickListener(object:BaseRecyclerViewAdapter.OnItemClickListener<ShipAddress>{
            override fun onItemClick(item: ShipAddress, position: Int) {
                Bus.send(SelectAddressEvent(item))
                finish()
            }
        })
        mAddAddressBtn.onClick { startActivity<ShipAddressEditActivity>() }
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getShipAddressList()
    }

    override fun onGetShipAddressList(result: MutableList<ShipAddress>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
    }

    override fun onDeleteShipAddress(result: Boolean) {
        toast("地址删除${if (result) "成功" else "失败"}")
        if (result) loadData()
    }

    override fun onEditShipAddress(result: Boolean) {
        toast("设置默认${if (result) "成功" else "失败"}")
        if (result) loadData()
    }
}