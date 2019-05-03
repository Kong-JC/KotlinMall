package com.example.order.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.order.presenter.view.ShipAddressView
import com.example.order.service.ShipAddressService
import com.kotlin.order.data.protocol.ShipAddress
import javax.inject.Inject

class ShipAddressPresenter @Inject constructor() : BasePresenter<ShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService

    fun getShipAddressList() {
        if (!checkNetWork()) return
        mView.showLoading()
        shipAddressService.getShipAddressList().excute(object : BaseSubscriber<MutableList<ShipAddress>?>(mView) {
            override fun onNext(t: MutableList<ShipAddress>?) {
                mView.onGetShipAddressList(t)
            }
        }, lifecycleProvider)
    }
    fun deleteShipAddress(id: Int) {
        if (!checkNetWork()) return
        mView.showLoading()
        shipAddressService.deleteShipAddress(id).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onDeleteShipAddress(t)
            }
        }, lifecycleProvider)
    }
    fun editShipAddress(address:ShipAddress) {
        if (!checkNetWork()) return
        mView.showLoading()
        shipAddressService.editShipAddress(address).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onEditShipAddress(t)
            }
        }, lifecycleProvider)
    }

}