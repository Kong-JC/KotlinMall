package com.example.order.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.example.baselibrary.ext.startLoading
import com.example.baselibrary.ui.fragment.BaseMvpFragment
import com.example.order.R
import com.example.order.injection.component.DaggerOrderComponent
import com.example.order.injection.module.OrderModule
import com.example.order.presenter.OrderListPresenter
import com.example.order.presenter.view.OrderListView
import com.example.order.ui.activity.OrderDetailActivity
import com.kennyc.view.MultiStateView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.ui.adapter.OrderAdapter
import com.kotlin.provider.common.ProviderConstant
import kotlinx.android.synthetic.main.fragment_order.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class OrderFragment : BaseMvpFragment<OrderListPresenter>(), OrderListView {

    private lateinit var mAdapter: OrderAdapter

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(activityComponent)
                .orderModule(OrderModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun setContentView(): Int = R.layout.fragment_order

    override fun initView() {
        loadData()
        mAdapter = OrderAdapter(activity!!)
        mOrderRv.layoutManager = LinearLayoutManager(activity)
        mOrderRv.adapter = mAdapter

        mAdapter.listener = object : OrderAdapter.OnOptClickListener {
            override fun onOptClick(optType: Int, order: Order) {
                when (optType) {
                    OrderConstant.OPT_ORDER_PAY -> {
                        toast("支付")
                    }
                    OrderConstant.OPT_ORDER_CONFIRM -> {
                        AlertView("确认收货", "是否确认收货？", "否", null, arrayOf("是"), activity,
                                AlertView.Style.Alert, OnItemClickListener { o, position ->
                            if (position == 0) mPresenter.confirmOrder(order.id)
                        }).show()
                    }
                    OrderConstant.OPT_ORDER_CANCEL -> {
                        AlertView("取消订单", "是否取消该订单？", "否", null, arrayOf("是"), activity,
                                AlertView.Style.Alert, OnItemClickListener { o, position ->
                            if (position == 0) mPresenter.cancelOrder(order.id)
                        }).show()
                    }
                }
            }
        }

        mAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Order> {
            override fun onItemClick(item: Order, position: Int) {
                startActivity<OrderDetailActivity>(ProviderConstant.KEY_ORDER_ID to item.id)
            }
        })

    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getOrderList(arguments!!.getInt(OrderConstant.KEY_ORDER_STATUS, -1))
    }

    override fun onGetOrderListResult(result: MutableList<Order>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
    }

    override fun onCancelOrderResult(result: Boolean) {
        toast("订单取消${if (result) "成功" else "失败"}")
        if (result) loadData()
    }

    override fun onConfirmOrderResult(result: Boolean) {
        toast("订单确认${if (result) "成功" else "失败"}")
        if (result) loadData()
    }

}