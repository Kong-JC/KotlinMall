package com.example.usercenter.data.api

import com.example.baselibrary.data.protocol.BaseResponse
import retrofit2.http.POST
import rx.Observable

interface UploadApi {
    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResponse<String>>
}