package com.example.usercenter.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.example.usercenter.data.protocol.UserInfo

interface UserInfoView:BaseView {
    fun onGetUploadTokenResult(result:String)
    fun onEditUserResult(result:UserInfo)
}