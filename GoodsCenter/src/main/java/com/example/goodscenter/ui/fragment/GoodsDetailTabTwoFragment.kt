package com.kotlin.goodscenter.ui.fragment

import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.example.baselibrary.ext.loadUrl
import com.example.baselibrary.ui.fragment.BaseFragment
import com.example.goodscenter.R
import com.kotlin.goodscenter.event.GoodsDetailImageEvent
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_two.*

/*
    商品详情Tab Two
 */
class GoodsDetailTabTwoFragment : BaseFragment() {

    override fun setContentView(): Int = R.layout.fragment_goods_detail_tab_two

    override fun initView() = initObserve()

    // 初始化监听，商品详情获取成功后，加载当前页面
    private fun initObserve() {
        Bus.observe<GoodsDetailImageEvent>().subscribe { t: GoodsDetailImageEvent ->
            run {
                mGoodsDetailOneIv.loadUrl(t.imgOne)
                mGoodsDetailTwoIv.loadUrl(t.imgTwo)
            }
        }.registerInBus(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}
