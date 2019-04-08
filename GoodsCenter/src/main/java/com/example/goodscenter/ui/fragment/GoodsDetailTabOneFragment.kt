package com.kotlin.goodscenter.ui.fragment

import android.view.Gravity
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.baselibrary.ui.fragment.BaseMvpFragment
import com.example.baselibrary.widgets.BannerImageLoader
import com.example.goodscenter.R
import com.example.goodscenter.iniection.component.DaggerGoodsComponent
import com.example.goodscenter.iniection.module.GoodsModule
import com.example.goodscenter.ui.activity.GoodsDetailActivity
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.goodscenter.common.GoodsConstant
import com.kotlin.goodscenter.data.protocol.Goods
import com.kotlin.goodscenter.event.AddCartEvent
import com.kotlin.goodscenter.event.GoodsDetailImageEvent
import com.kotlin.goodscenter.event.SkuChangedEvent
import com.kotlin.goodscenter.event.UpdateCartSizeEvent
import com.kotlin.goodscenter.presenter.GoodsDetailPresenter
import com.kotlin.goodscenter.presenter.view.GoodsDetailView
import com.kotlin.goodscenter.widget.GoodsSkuPopView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import org.jetbrains.anko.contentView

/*
    商品详情Tab One
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {

    override fun setContentView(): Int = R.layout.fragment_goods_detail_tab_one

    private lateinit var mSkuPop: GoodsSkuPopView
    //SKU弹层出场动画
    private lateinit var mAnimationStart: Animation
    //SKU弹层退场动画
    private lateinit var mAnimationEnd: Animation

    private var mCurGoods:Goods? = null

    // 初始化视图
    override fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        mGoodsDetailBanner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)

        //sku弹层
        mSkuView.onClick {
            mSkuPop.showAtLocation((activity as GoodsDetailActivity).contentView,
                    Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL, 0, 0
            )
            (activity as BaseActivity).contentView!!.startAnimation(mAnimationStart)
        }

        initAnim()
        initSkuPop()
        loadData()
        initObserve()
    }

    // 初始化缩放动画
    private fun initAnim() {
        mAnimationStart = ScaleAnimation(
                1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationStart.duration = 500
        mAnimationStart.fillAfter = true

        mAnimationEnd = ScaleAnimation(
                0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationEnd.duration = 500
        mAnimationEnd.fillAfter = true
    }

    // 初始化sku弹层
    private fun initSkuPop() {
        mSkuPop = GoodsSkuPopView(activity!!)
        mSkuPop.setOnDismissListener {
            (activity as BaseActivity).contentView!!.startAnimation(mAnimationEnd)
        }
    }

    // 加载数据
    private fun loadData() {
        mPresenter.getGoodsDetailList(activity!!.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    // Dagger注册
    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent)
                .goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }

    // 获取商品详情 回调
    override fun onGetGoodsDetailResult(result: Goods) {
        mCurGoods = result

        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))

        loadPopData(result)
    }

    // 加载SKU数据
    private fun loadPopData(result: Goods) {
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)

        mSkuPop.setSkuData(result.goodsSku)

    }

    // 监听SKU变化及加入购物车事件
    private fun initObserve() {
        Bus.observe<SkuChangedEvent>().subscribe {
            mSkuSelectedTv.text = mSkuPop.getSelectSku() + GoodsConstant.SKU_SEPARATOR + mSkuPop.getSelectCount() + "件"
        }.registerInBus(this)

        Bus.observe<AddCartEvent>().subscribe {
            addCart()
        }.registerInBus(this)
    }

    // 取消事件监听
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    // 加入购物车
    private fun addCart() {
        mCurGoods?.let {
            mPresenter.addCart(it.id,
                    it.goodsDesc,
                    it.goodsDefaultIcon,
                    it.goodsDefaultPrice,
                    mSkuPop.getSelectCount(),
                    mSkuPop.getSelectSku()
                    )
        }
    }

    // 加入购物车 回调
    override fun onAddCartResult(result: Int) {
//        toast("Cart - $result")
        Bus.send(UpdateCartSizeEvent())
    }

}
