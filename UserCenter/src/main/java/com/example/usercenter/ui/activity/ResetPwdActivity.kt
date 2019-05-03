package com.example.usercenter.ui.activity

import com.example.baselibrary.ext.enabled
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.usercenter.R
import com.example.usercenter.injection.component.DaggerUserComponent
import com.example.usercenter.injection.module.UserModule
import com.example.usercenter.presenter.ResetPwdPresenter
import com.example.usercenter.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

/**
 * 重置密码界面
 */
class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView {


    override fun setView(): Int = R.layout.activity_reset_pwd

    // 初始化视图
    override fun initView() {
        // super.initView()
        mConfirmBtn.onClick {
            if (mPwdEt.text.toString() == mPwdConfirmEt.text.toString())
                mPresenter.resetPwd(intent.getStringExtra("mobile"), mPwdConfirmEt.text.toString())
            else toast("密码不一致")
        }
        mConfirmBtn.enabled(arrayOf(mPwdEt, mPwdConfirmEt))
    }

    // Dagger 注入
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent)
                .userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onResetPwdResult(result: String) {
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }

}
