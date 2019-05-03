package com.example.order.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.order.presenter.view.EditShipAddressView
import com.example.order.service.ShipAddressService
import com.kotlin.order.data.protocol.ShipAddress
import javax.inject.Inject

class EditShipAddressPresenter @Inject constructor() : BasePresenter<EditShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService

    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String) {
        if (!checkNetWork()) return
        mView.showLoading()
        shipAddressService.addShipAddress(shipUserName,shipUserMobile,shipAddress).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onAddShipAddressResult(t)
            }
        }, lifecycleProvider)
    }

    fun editShipAddress(address: ShipAddress) {
        if (!checkNetWork()) return
        mView.showLoading()
        shipAddressService.editShipAddress(address).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onEditShipAddressResult(t)
            }
        }, lifecycleProvider)
    }

}