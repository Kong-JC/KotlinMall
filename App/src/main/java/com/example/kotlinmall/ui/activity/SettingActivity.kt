package com.example.kotlinmall.ui.activity

import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.kotlinmall.R
import com.kotlin.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.toast

/*
    设置界面
 */
class SettingActivity : BaseActivity() {
    override fun setView(): Int = R.layout.activity_setting

    override fun initView() {
        mUserProtocolTv.onClick { toast("用户协议") }
        mFeedBackTv.onClick { toast("反馈意见") }
        mAboutTv.onClick { toast("关于") }

        //退出登录，清空本地用户数据
        mLogoutBtn.onClick {
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }

}
