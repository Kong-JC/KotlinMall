package com.example.usercenter.injection.component

import com.example.baselibrary.injection.PresenterComponentScope
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.usercenter.injection.module.UploadModule
import com.example.usercenter.injection.module.UserModule
import com.example.usercenter.ui.activity.*
import dagger.Component

@PresenterComponentScope
@Component(dependencies = [ActivityComponent::class],modules = arrayOf(UserModule::class,UploadModule::class))
interface UserComponent {
    fun inject(activity:RegisterActivity)
    fun inject(activity:LoginActivity)
    fun inject(activity:ForgetPwdActivity)
    fun inject(activity:ResetPwdActivity)
    fun inject(activity:UserInfoActivity)

}