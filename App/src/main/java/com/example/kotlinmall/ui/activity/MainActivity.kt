package com.example.kotlinmall.ui.activity

import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.goodscenter.ui.fragment.CategoryFragment
import com.example.kotlinmall.R
import com.example.kotlinmall.ui.fragment.HomeFragment
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.goodscenter.common.GoodsConstant
import com.kotlin.goodscenter.event.UpdateCartSizeEvent
import com.kotlin.goodscenter.ui.fragment.CartFragment
import com.kotlin.mall.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * BaseLibrary 模块
 * 用于存放基础控件，常用工具类，父类等
 *
 * Provider
 * 存放和业务相关的属性
 */
class MainActivity : BaseActivity() {

    private val mStack = Stack<Fragment>()
    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { CategoryFragment() }
//    private val mCategoryFragment by lazy { HomeFragment() }
//    private val mCartFragment by lazy { HomeFragment() }
    private val mCartFragment by lazy { CartFragment() }
    private val mMsgFragment by lazy { HomeFragment() }
    private val mMeFragment by lazy { MeFragment() }

    override fun setView(): Int = R.layout.activity_main

    override fun initView() {
        initFragment()
        initBottomNav()
        changeFragment(0)
        initObserve()
        loadCartSize()
    }

    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.mContainer, mHomeFragment)
        manager.add(R.id.mContainer, mCategoryFragment)
        manager.add(R.id.mContainer, mCartFragment)
        manager.add(R.id.mContainer, mMsgFragment)
        manager.add(R.id.mContainer, mMeFragment)
        manager.commit()
        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMeFragment)
    }

    private fun initBottomNav() {
        mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabReselected(position: Int) {}

            override fun onTabUnselected(position: Int) {}

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }
        })
    }

    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        for (fragment in mStack) manager.hide(fragment)
        manager.show(mStack[position]).commit()
    }

    // 监听购物车数量变化
    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>().subscribe {
            loadCartSize()
        }.registerInBus(this)
    }

    private fun loadCartSize(){
        mBottomNavBar.checkCartBadge(AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE))
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}
