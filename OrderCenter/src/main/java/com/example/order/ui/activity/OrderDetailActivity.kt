package com.example.order.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.order.R
import com.example.order.injection.component.DaggerOrderComponent
import com.example.order.injection.module.OrderModule
import com.example.order.presenter.OrderDetailPresenter
import com.example.order.presenter.view.OrderDetailView
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.ui.adapter.OrderGoodsAdapter
import com.kotlin.provider.common.ProviderConstant
import kotlinx.android.synthetic.main.activity_order_detail.*

class OrderDetailActivity : BaseMvpActivity<OrderDetailPresenter>(), OrderDetailView {

    private lateinit var mAdapter: OrderGoodsAdapter

    override fun setView(): Int = R.layout.activity_order_detail

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(activityComponent)
                .orderModule(OrderModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun initView() {
        initRecyclerView()
        mPresenter.getOrderById(intent.getIntExtra(ProviderConstant.KEY_ORDER_ID,-1))
    }

    private fun initRecyclerView() {
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mOrderGoodsRv.adapter = mAdapter
    }

    override fun onGetOrderByIdResult(result: Order) {
        mShipNameTv.setContentText(result.shipAddress!!.shipUserName)
        mShipMobileTv.setContentText(result.shipAddress!!.shipUserMobile)
        mShipAddressTv.setContentText(result.shipAddress!!.shipAddress)
        mTotalPriceTv.setContentText(YuanFenConverter.changeF2YWithUnit(result.totalPrice))
        mAdapter.setData(result.orderGoodsList)
    }

}