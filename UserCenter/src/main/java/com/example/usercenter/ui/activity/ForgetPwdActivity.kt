package com.example.usercenter.ui.activity

import android.view.View
import com.example.baselibrary.ext.enabled
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.usercenter.R
import com.example.usercenter.injection.component.DaggerUserComponent
import com.example.usercenter.injection.module.UserModule
import com.example.usercenter.presenter.ForgetPwdPresenter
import com.example.usercenter.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity

/**
 * 忘记密码界面
 */
class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {

    override fun setView(): Int = R.layout.activity_forget_pwd

    // 初始化视图
    override fun initView() {
        // super.initView()
        mNextBtn.onClick(this)
        mVerifyCodeBtn.onClick(this)
        mNextBtn.enabled(arrayOf(mMobileEt, mVerifyCodeEt))
        mVerifyCodeBtn.enabled(arrayOf(mMobileEt))
    }

    // Dagger 注入
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent)
                .userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onForgetPwdResult(result: String) {
//        toast(result)
        startActivity<ResetPwdActivity>("mobile" to mMobileEt.text.toString())
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                mVerifyCodeEt.setText("123456")
            }
            R.id.mNextBtn -> mPresenter.forgetPwd(mMobileEt.text.toString(),
                    mVerifyCodeEt.text.toString())
        }
    }

}
