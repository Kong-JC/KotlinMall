package com.example.order.ui.activity

import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.order.R
import com.example.order.injection.component.DaggerShipAddressComponent
import com.example.order.injection.module.ShipAddressModule
import com.example.order.presenter.EditShipAddressPresenter
import com.example.order.presenter.view.EditShipAddressView
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.data.protocol.ShipAddress
import kotlinx.android.synthetic.main.activity_edit_address.*
import org.jetbrains.anko.toast

class ShipAddressEditActivity : BaseMvpActivity<EditShipAddressPresenter>(), EditShipAddressView {
    private var mAddress: ShipAddress? = null

    override fun setView(): Int = R.layout.activity_edit_address

    override fun injectComponent() {
        DaggerShipAddressComponent.builder().activityComponent(activityComponent)
                .shipAddressModule(ShipAddressModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun initView() {
        // super.initView()
        mSaveBtn.onClick {
            if (mShipNameEt.text.isNullOrEmpty()) {
                toast("收货人不能为空")
                return@onClick
            }
            if (mShipMobileEt.text.isNullOrEmpty()) {
                toast("联系方式不能为空")
                return@onClick
            }
            if (mShipAddressEt.text.isNullOrEmpty()) {
                toast("详细地址不能为空")
                return@onClick
            }
            if (mAddress == null) {
                mPresenter.addShipAddress(mShipNameEt.text.toString(),
                        mShipMobileEt.text.toString(), mShipAddressEt.text.toString())
            } else {
                mAddress!!.shipUserName = mShipNameEt.text.toString()
                mAddress!!.shipUserMobile = mShipMobileEt.text.toString()
                mAddress!!.shipAddress = mShipAddressEt.text.toString()
                mPresenter.editShipAddress(mAddress!!)
            }
        }
        initData()
    }

    private fun initData() {
        mAddress = intent.getParcelableExtra(OrderConstant.KEY_SHIP_ADDRESS)
        mAddress?.let {
            mShipNameEt.setText(it.shipUserName)
            mShipMobileEt.setText(it.shipUserMobile)
            mShipAddressEt.setText(it.shipAddress)
        }
    }

    override fun onAddShipAddressResult(result: Boolean) {
        toast("添加地址成功 ")
        finish()
    }

    override fun onEditShipAddressResult(result: Boolean) {
        toast("地址修改成功")
        finish()
    }

}