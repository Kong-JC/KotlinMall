package com.example.usercenter.ui.activity

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.baselibrary.ext.enabled
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.usercenter.R
import com.example.usercenter.data.protocol.UserInfo
import com.example.usercenter.injection.component.DaggerUserComponent
import com.example.usercenter.injection.module.UserModule
import com.example.usercenter.presenter.LoginPresenter
import com.example.usercenter.presenter.view.LoginView
import com.kotlin.provider.router.RouterPath
import com.kotlin.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 登录界面
 */
@Route(path = RouterPath.UserCenter.PATH_LOGIN)
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {

    override fun setView(): Int = R.layout.activity_login

    // 初始化视图
    override fun initView() {
        super.initView()
        mLoginBtn.enabled(arrayOf(mMobileEt, mPwdEt))
        mLoginBtn.onClick(this)
        mHeaderBar.getRightView().onClick(this)
        mForgetPwdTv.onClick(this)

        mMobileEt.setText("13800138000")
        mPwdEt.setText("123")
    }

    // Dagger 注入
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent)
                .userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    // 登录回调
    override fun onLoginResult(result: UserInfo) {
        toast("登录成功")
        UserPrefsUtils.putUserInfo(result)
        finish()
//        startActivity<UserInfoActivity>()
//        startActivity<MainAc>()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mRightTv -> {startActivity<RegisterActivity>()}
            R.id.mLoginBtn ->
                mPresenter.login(mMobileEt.text.toString(), mPwdEt.text.toString(), "")
            R.id.mForgetPwdTv -> {startActivity<ForgetPwdActivity>()}
        }
    }


}
