package com.example.usercenter.data.repository

import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.data.protocol.BaseResponse
import com.example.usercenter.data.api.UploadApi
import rx.Observable
import javax.inject.Inject

class UploadRepository @Inject constructor() {
    fun getUploadToken(): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UploadApi::class.java).getUploadToken()
    }

}