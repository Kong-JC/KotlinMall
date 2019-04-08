package com.example.usercenter.ui.activity

import android.view.View
import com.example.baselibrary.common.AppManager
import com.example.baselibrary.ext.enabled
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.usercenter.R
import com.example.usercenter.injection.component.DaggerUserComponent
import com.example.usercenter.injection.module.UserModule
import com.example.usercenter.presenter.RegisterPresenter
import com.example.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 * 注册界面
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {

    private var pressTime: Long = 0

    override fun setView(): Int = R.layout.activity_register

    // 初始化视图
    override fun initView() {
        super.initView()
        mRegisterBtn.enabled(arrayOf(mMobileEt, mVerifyCodeEt, mPwdEt, mPwdConfirmEt))
        mVerifyCodeBtn.onClick(this)
        mRegisterBtn.onClick(this)
    }

    // Dagger 注入
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent)
                .userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    // 注册回调
    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast("再按一次退出程序")
            pressTime = time
        } else AppManager.instance.exitApp(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                mVerifyCodeEt.setText("123")
            }
            R.id.mRegisterBtn -> mPresenter.register(mMobileEt.text.toString(),
                    mPwdEt.text.toString(), mVerifyCodeEt.text.toString())
        }
    }

}
