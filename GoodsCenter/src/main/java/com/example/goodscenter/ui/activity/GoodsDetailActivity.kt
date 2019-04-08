package com.example.goodscenter.ui.activity

import android.view.Gravity
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.goodscenter.R
import com.google.android.material.tabs.TabLayout
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.goodscenter.common.GoodsConstant
import com.kotlin.goodscenter.event.AddCartEvent
import com.kotlin.goodscenter.event.UpdateCartSizeEvent
import com.kotlin.goodscenter.ui.adapter.GoodsDetailVpAdapter
import com.kotlin.provider.common.afterLogin
import kotlinx.android.synthetic.main.activity_goods_detail.*
import org.jetbrains.anko.startActivity
import q.rorbin.badgeview.QBadgeView

// 商品详情 Activity
class GoodsDetailActivity : BaseActivity() {

    override fun setView(): Int = R.layout.activity_goods_detail

    private lateinit var mCartBdage: QBadgeView

    // 初始化视图
    override fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        //TabLayout关联ViewPager
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mAddCartBtn.onClick { afterLogin { Bus.send(AddCartEvent()) } }

        mEnterCartTv.onClick { startActivity<CartActivity>() }

        mLeftIv.onClick { finish() }

        mCartBdage = QBadgeView(this)

        initObserve()
        loadCartSize()
    }

    // 加载购物车数量
    private fun loadCartSize() = setCartBadge()

    // 监听购物车数量变化
    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>().subscribe {
            setCartBadge()
        }.registerInBus(this)
    }

    // 设置购物车标签
    private fun setCartBadge() {
        mCartBdage.badgeGravity = Gravity.END or Gravity.TOP
        mCartBdage.setGravityOffset(22f, -2f, true)
        mCartBdage.setBadgeTextSize(6f, true)
        mCartBdage.bindTarget(mEnterCartTv).badgeNumber = AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE)
    }

    // Bus取消监听
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}
