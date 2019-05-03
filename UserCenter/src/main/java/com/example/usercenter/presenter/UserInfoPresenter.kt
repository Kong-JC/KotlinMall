package com.example.usercenter.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.usercenter.data.protocol.UserInfo
import com.example.usercenter.presenter.view.UserInfoView
import com.example.usercenter.service.UploadService
import com.example.usercenter.service.UserService
import javax.inject.Inject

class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var uploadService: UploadService

    fun getUploadToken() {
        if (!checkNetWork()) return
        mView.showLoading()
        uploadService.getUploadToken().excute(object : BaseSubscriber<String>(mView) {
            override fun onNext(t: String) {
                mView.onGetUploadTokenResult(t)
            }
        }, lifecycleProvider)
    }

    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String) {
        if (!checkNetWork()) return
        mView.showLoading()
        userService.editUser(userIcon, userName, userGender, userSign).excute(object : BaseSubscriber<UserInfo>(mView) {
            override fun onNext(t: UserInfo) {
                mView.onEditUserResult(t)
            }
        }, lifecycleProvider)
    }

}