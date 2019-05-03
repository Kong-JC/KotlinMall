package com.example.order.ui.activity

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.example.baselibrary.ext.hideView
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ext.showView
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.order.R
import com.example.order.event.SelectAddressEvent
import com.example.order.injection.component.DaggerOrderComponent
import com.example.order.injection.module.OrderModule
import com.example.order.presenter.OrderConfirmPresenter
import com.example.order.presenter.view.OrderConfirmView
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.ui.adapter.OrderGoodsAdapter
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity : BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView {

    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId: Int = 0
    private lateinit var mAdapter: OrderGoodsAdapter
    private var mOrder: Order? = null

    override fun setView(): Int = R.layout.activity_order_confirm

    override fun initView() {
        // super.initView()

        mShipView.onClick { startActivity<ShipAddressActivity>() }
        mSelectShipTv.onClick { startActivity<ShipAddressActivity>() }

        mSubmitOrderBtn.onClick { mOrder?.let { mPresenter.submitOrder(it) } }

        mPresenter.getOrderById(mOrderId)

        initRecyclerView()

        initObserve()
    }

    private fun initObserve() {
        Bus.observe<SelectAddressEvent>().subscribe { t: SelectAddressEvent ->
            run {
                mOrder?.let { it.shipAddress = t.address }
                updateAddressView()
            }
        }.registerInBus(this)
    }

    private fun updateAddressView() {
        mOrder?.let {
            Log.d("OrderConfirmActivity", " -=-=-=-=- updateAddressView it.shipAddress:${it.shipAddress}")
            if (it.shipAddress == null) {
                hideView(arrayOf(mShipView))
                showView(arrayOf(mSelectShipTv))
            } else {
                hideView(arrayOf(mSelectShipTv))
                showView(arrayOf(mShipView))
                mShipNameTv.text = "${it.shipAddress!!.shipUserName}  ${it.shipAddress!!.shipUserMobile}"
                mShipAddressTv.text = it.shipAddress!!.shipAddress
            }
        }
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(activityComponent)
                .orderModule(OrderModule()).build().inject(this)
        mPresenter.mView = this
    }

    private fun initRecyclerView() {
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mOrderGoodsRv.adapter = mAdapter
    }

    override fun onGetOrderByIdResult(result: Order) {
        mOrder = result
        mAdapter.setData(result.orderGoodsList)
        mTotalPriceTv.text = "合计: ${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"
        updateAddressView()
    }

    override fun onSubmitOrderResult(result: Boolean) {
        toast("订单提交${if (result) "成功" else "失败"}")
        if (result) finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}