package com.example.usercenter.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.usercenter.presenter.view.RegisterView
import com.example.usercenter.service.UserService
import javax.inject.Inject

class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService: UserService

    fun register(mobile: String, pwd: String, verifyCode: String) {
        if (!checkNetWork()) return
        mView.showLoading()
        userService.register(mobile, pwd, verifyCode).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                if (t) mView.onRegisterResult("注册成功")
            }
        }, lifecycleProvider)
    }

}