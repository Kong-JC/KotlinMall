package com.example.order.service.impl

import com.example.baselibrary.ext.convert
import com.example.baselibrary.ext.convertBoolean
import com.example.order.service.ShipAddressService
import com.kotlin.order.data.protocol.ShipAddress
import com.kotlin.order.data.repository.ShipAddressRepository
import rx.Observable
import javax.inject.Inject

class ShipAddressServiceImpl @Inject constructor():ShipAddressService{
    @Inject
    lateinit var repository: ShipAddressRepository

    override fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<Boolean> {
        return repository.addShipAddress(shipUserName,shipUserMobile,shipAddress).convertBoolean()
    }

    override fun deleteShipAddress(id: Int): Observable<Boolean> {
        return repository.deleteShipAddress(id).convertBoolean()
    }

    override fun editShipAddress(address: ShipAddress): Observable<Boolean> {
        return repository.editShipAddress(address).convertBoolean()
    }

    override fun getShipAddressList(): Observable<MutableList<ShipAddress>?> {
        return repository.getShipAddressList().convert()
    }

}