package com.example.goodscenter.ui.fragment

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baselibrary.ext.hideView
import com.example.baselibrary.ext.showView
import com.example.baselibrary.ext.startLoading
import com.example.baselibrary.ui.fragment.BaseMvpFragment
import com.example.goodscenter.R
import com.example.goodscenter.data.protocol.Category
import com.example.goodscenter.iniection.component.DaggerCategoryComponent
import com.example.goodscenter.iniection.module.CategoryModule
import com.example.goodscenter.presenter.CategoryPresenter
import com.example.goodscenter.presenter.view.CategoryView
import com.example.goodscenter.ui.activity.GoodsActivity
import com.example.goodscenter.ui.adapter.SecondCategoryAdapter
import com.example.goodscenter.ui.adapter.TopCategoryAdapter
import com.kennyc.view.MultiStateView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.goodscenter.common.GoodsConstant
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.support.v4.startActivity

class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {

    lateinit var topAdapter: TopCategoryAdapter
    lateinit var secondAdapter: SecondCategoryAdapter

    override fun setContentView(): Int = R.layout.fragment_category

    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(activityComponent)
                .categoryModule(CategoryModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun initView() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(context!!)
        topAdapter = TopCategoryAdapter(context!!)
        mTopCategoryRv.adapter = topAdapter
        topAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                for (category in topAdapter.dataList)
                    category.isSelected = item.id == category.id
                topAdapter.notifyDataSetChanged()
                loadData(item.id)
            }
        })
        mSecondCategoryRv.layoutManager = GridLayoutManager(context, 3)
        secondAdapter = SecondCategoryAdapter(context!!)
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                startActivity<GoodsActivity>((GoodsConstant.KEY_CATEGORY_ID to item.id))
            }
        })
        loadData()
    }

    fun loadData(id: Int = 0) {
        if (id != 0) mMultiStateView.startLoading()
//        Observable.timer(1, TimeUnit.SECONDS).subscribe { mPresenter.getCategory(id) } // 延时加载
        mPresenter.getCategory(id)
    }

    override fun onCategoryResult(result: MutableList<Category>?) {
        if (result != null && result.size > 0) {
            result?.let {
                if (result[0].parentId == 0) {
                    result[0].isSelected = true
                    topAdapter.setData(result)
                    mPresenter.getCategory(result[0].id)
                } else {
                    secondAdapter.setData(result)
                    mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
                    showView(arrayOf(mTopCategoryIv, mCategoryTitleTv))
                }
            }
        } else {
            hideView(arrayOf(mTopCategoryIv, mCategoryTitleTv))
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

}