package com.example.kotlinmall.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.fragment.BaseFragment
import com.example.baselibrary.widgets.BannerImageLoader
import com.example.goodscenter.ui.activity.SearchGoodsActivity
import com.example.kotlinmall.R
import com.example.kotlinmall.common.HOME_BANNER_LIST
import com.example.kotlinmall.common.HOME_DISCOUNT_LIST
import com.example.kotlinmall.common.HOME_TOPIC_LIST
import com.example.kotlinmall.ui.adapter.HomeDiscountAdapter
import com.kotlin.mall.ui.adapter.TopicAdapter
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.support.v4.startActivity

class HomeFragment : BaseFragment() {

    override fun setContentView(): Int = R.layout.fragment_home

    override fun initView() {
        initBanner()
        initNews()
        initDiscount()
        initTopic()
        mSearchEt.onClick { startActivity<SearchGoodsActivity>() }
    }

    private fun initBanner() {
        mHomeBanner.setImageLoader(BannerImageLoader())
        mHomeBanner.setImages(HOME_BANNER_LIST)
        mHomeBanner.setBannerAnimation(Transformer.Accordion)
        mHomeBanner.setDelayTime(2000)
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT)
        mHomeBanner.start()
    }

    private fun initNews() {
        mNewsFlipperView.setData(arrayOf("公告 1", "公告 2", "公告 3"))
    }

    private fun initDiscount() {
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        mHomeDiscountRv.layoutManager = manager

        val discountAdapter = HomeDiscountAdapter(context!!)
        mHomeDiscountRv.adapter = discountAdapter
        discountAdapter.setData(HOME_DISCOUNT_LIST)
    }

    /*
    初始化主题
 */
    private fun initTopic() {
        //话题
        mTopicPager.adapter = TopicAdapter(context!!, HOME_TOPIC_LIST)
        mTopicPager.currentItem = 1
        mTopicPager.offscreenPageLimit = 5

        CoverFlow.Builder().with(mTopicPager).scale(0.3f).pagerMargin(-30.0f).spaceSize(0.0f).build()
    }

}
