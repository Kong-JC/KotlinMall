package com.kotlin.goodscenter.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.order.ui.fragment.OrderFragment
import com.kotlin.order.common.OrderConstant

/*
    商品详情ViewPager Adapter
 */
class OrderVpAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val titles = arrayOf("全部", "待付款", "待收货", "已完成", "已取消")

    override fun getItem(position: Int): Fragment {
        val fragment = OrderFragment()
        val bundle = Bundle()
        bundle.putInt(OrderConstant.KEY_ORDER_STATUS, position)
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence = titles[position]

}
