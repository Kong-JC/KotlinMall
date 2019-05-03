package com.kotlin.mall.ui.fragment

import android.view.View
import com.example.baselibrary.ext.loadUrl
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.fragment.BaseFragment
import com.example.kotlinmall.R
import com.example.kotlinmall.ui.activity.SettingActivity
import com.example.order.ui.activity.OrderActivity
import com.example.order.ui.activity.ShipAddressActivity
import com.example.usercenter.ui.activity.LoginActivity
import com.example.usercenter.ui.activity.UserInfoActivity
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.common.OrderStatus
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.provider.common.afterLogin
import com.kotlin.provider.common.isLogined
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.startActivity

/*
    "我的"界面
 */
class MeFragment : BaseFragment(), View.OnClickListener {
    override fun setContentView(): Int = R.layout.fragment_me

    override fun initView() {
        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)

        mWaitPayOrderTv.onClick(this)
        mWaitConfirmOrderTv.onClick(this)
        mCompleteOrderTv.onClick(this)
        mAllOrderTv.onClick(this)
        mAddressTv.onClick(this)
        mShareTv.onClick(this)
        mSettingTv.onClick(this)
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    // 加载初始数据
    private fun loadData() {
        if (isLogined()) {
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()) mUserIconIv.loadUrl(userIcon)
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        } else {
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        }

    }

    // 点击事件
    override fun onClick(view: View) {
        when (view.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> {
//                afterLogin(startActivity<UserInfoActivity>(),startActivity<LoginActivity>())
                if (isLogined()) startActivity<UserInfoActivity>() else startActivity<LoginActivity>()
            }

            R.id.mWaitPayOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_PAY)
            }
            R.id.mWaitConfirmOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_CONFIRM)
            }
            R.id.mCompleteOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_COMPLETED)
            }
            R.id.mAllOrderTv -> {
                afterLogin { startActivity<OrderActivity>() }
            }
            R.id.mAddressTv -> {
                afterLogin { startActivity<ShipAddressActivity>() }
            }
            R.id.mShareTv -> {
//                toast(R.string.coming_soon_tip)
            }
            R.id.mSettingTv -> {
                startActivity<SettingActivity>()
            }
        }
    }

}
