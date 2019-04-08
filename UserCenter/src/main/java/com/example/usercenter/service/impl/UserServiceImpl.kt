package com.example.usercenter.service.impl

import com.example.baselibrary.ext.convert
import com.example.baselibrary.ext.convertBoolean
import com.example.usercenter.data.protocol.UserInfo
import com.example.usercenter.data.repository.UserRepository
import com.example.usercenter.service.UserService
import rx.Observable
import javax.inject.Inject

class UserServiceImpl @Inject constructor() : UserService {
    @Inject
    lateinit var repository: UserRepository

    // 注册
    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
//        return repository.register(mobile, pwd, verifyCode).flatMap(BaseFuncBoolean())
        return repository.register(mobile, pwd, verifyCode).convertBoolean()
    }

    // 登录
    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return repository.login(mobile, pwd, pushId).convert()
    }

    // 忘记密码
    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return repository.forgetPwd(mobile, verifyCode).convertBoolean()
    }

    // 重置密码
    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return repository.resetPwd(mobile, pwd).convertBoolean()
    }

    // 修改用户信息
    override fun editUser(userIcon: String, userName: String, userGender: String, userSign: String):
            Observable<UserInfo> {
        return repository.editUser(userIcon, userName,userGender,userSign).convert()
    }

}