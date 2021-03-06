package com.example.goodscenter.ui.activity

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ext.setVisibility
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.goodscenter.R
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.goodscenter.common.GoodsConstant
import com.kotlin.goodscenter.ui.adapter.SearchHistoryAdapter
import kotlinx.android.synthetic.main.activity_search_goods.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/*
    关键字搜索商品页
 */
class SearchGoodsActivity : BaseActivity(), View.OnClickListener {
    override fun setView(): Int =R.layout.activity_search_goods

    private lateinit var mAdapter: SearchHistoryAdapter

    override fun onStart() {
        super.onStart()
        loadData()
    }

    /*
        初始化视图
     */
    override fun initView() {
        mLeftIv.onClick(this)
        mSearchTv.onClick(this)
        mClearBtn.onClick(this)
        //RecyclerView适配器
        mAdapter = SearchHistoryAdapter(this)
        mSearchHistoryRv.layoutManager = LinearLayoutManager(this)
        mSearchHistoryRv.adapter = mAdapter
        //item点击事件
        mAdapter.mItemClickListener = object : BaseRecyclerViewAdapter.OnItemClickListener<String> {
            override fun onItemClick(item: String, position: Int) {
                enterGoodsList(item)
            }
        }
    }

    /*
        从SP中加载数据
     */
    private fun loadData() {
        val set = AppPrefsUtils.getStringSet(GoodsConstant.SP_SEARCH_HISTORY)
        mNoDataTv.setVisibility(set.isEmpty())
        mDataView.setVisibility(set.isNotEmpty())
        if (set.isNotEmpty()) {
            val list = mutableListOf<String>()
            list.addAll(set)
            mAdapter.setData(list)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mLeftIv -> finish()
            //执行搜索
            R.id.mSearchTv -> doSearch()
            //清除历史记录
            R.id.mClearBtn -> {
                AppPrefsUtils.remove(GoodsConstant.SP_SEARCH_HISTORY)
                loadData()
            }
        }
    }

    //搜索
    private fun doSearch() {
        if (mKeywordEt.text.isNullOrEmpty()) {
            toast("请输入需要搜索的关键字")
        } else {
            val inputValue = mKeywordEt.text.toString()
            AppPrefsUtils.putStringSet(GoodsConstant.SP_SEARCH_HISTORY, mutableSetOf(inputValue))
            enterGoodsList(inputValue)
        }
    }

    /*
        进入商品列表界面
     */
    private fun enterGoodsList(value: String) {
        //输入不为空，进入商品列表
        startActivity<GoodsActivity>(
                GoodsConstant.KEY_SEARCH_GOODS_TYPE to GoodsConstant.SEARCH_GOODS_TYPE_KEYWORD,
                GoodsConstant.KEY_GOODS_KEYWORD to value
        )

    }

}
