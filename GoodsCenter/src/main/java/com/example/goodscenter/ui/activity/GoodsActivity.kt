package com.example.goodscenter.ui.activity

import androidx.recyclerview.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import com.example.baselibrary.ext.startLoading
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.goodscenter.R
import com.example.goodscenter.iniection.component.DaggerGoodsComponent
import com.example.goodscenter.iniection.module.GoodsModule
import com.example.goodscenter.presenter.GoodsListPresenter
import com.example.goodscenter.presenter.view.GoodsListView
import com.example.goodscenter.ui.adapter.GoodsAdapter
import com.kennyc.view.MultiStateView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.goodscenter.common.GoodsConstant
import com.kotlin.goodscenter.data.protocol.Goods
import kotlinx.android.synthetic.main.activity_goods.*
import org.jetbrains.anko.startActivity

class GoodsActivity : BaseMvpActivity<GoodsListPresenter>(), GoodsListView/*, BGARefreshLayout.BGARefreshLayoutDelegate */{

    private lateinit var mGoodsAdapter: GoodsAdapter
    private var mCurrentPage: Int = 1
    private var mMaxPage: Int = 1
//    private var mData: MutableList<Goods>? = null

    override fun setView(): Int = R.layout.activity_goods

    override fun initView() {
        // super.initView()
        mGoodsAdapter = GoodsAdapter(this)
        mGoodsRv.layoutManager = GridLayoutManager(this, 2)
        mGoodsRv.adapter = mGoodsAdapter

        mGoodsAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Goods>{
            override fun onItemClick(item: Goods, position: Int) {
                startActivity<GoodsDetailActivity>(GoodsConstant.KEY_GOODS_ID to item.id)
            }
        })

        initRefreshLayout()
        loadData()
    }

    private fun initRefreshLayout() {
//        mRefreshLayout.setDelegate(this)
        val viewHolder = BGANormalRefreshViewHolder(this, true)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        viewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    private fun loadData() {
        if (intent.getIntExtra(GoodsConstant.KEY_SEARCH_GOODS_TYPE, 0) != 0) {
            mPresenter.getGoodsListByKeyword(intent.getStringExtra(GoodsConstant.KEY_GOODS_KEYWORD), mCurrentPage)
            mMultiStateView.startLoading()
        } else {
            mPresenter.getGoodsList(intent.getIntExtra(GoodsConstant.KEY_CATEGORY_ID, 1), mCurrentPage)
            mMultiStateView.startLoading()
        }
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent)
                .goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onGoodsListResult(result: MutableList<Goods>?) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result != null && result.size > 0) {
            mMaxPage = result[0].maxPage
            if (mCurrentPage == 1) mGoodsAdapter.setData(result)
            else {
                mGoodsAdapter.dataList.addAll(result)
                mGoodsAdapter.notifyDataSetChanged()
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
    }

//    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
//        return if (mCurrentPage < mMaxPage) {
//            mCurrentPage++
//            loadData()
//            true
//        } else false
//    }

//    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
//        mCurrentPage = 1
//        loadData()
//    }

}
