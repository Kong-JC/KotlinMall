package com.example.usercenter.data.api

import com.example.baselibrary.data.protocol.BaseResponse
import com.example.usercenter.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface UserApi {
    @POST("userCenter/register")
    fun register(@Body req: RegisterRequest): Observable<BaseResponse<String>>
    @POST("userCenter/login")
    fun login(@Body req: LoginRequest): Observable<BaseResponse<UserInfo>>
    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body req: ForgetPwdRequest): Observable<BaseResponse<String>>
    @POST("userCenter/resetPwd")
    fun resetPwd(@Body req: ResetPwdRequest): Observable<BaseResponse<String>>
    @POST("userCenter/editUser")
    fun editUser(@Body req: EditUserRequest): Observable<BaseResponse<UserInfo>>
}