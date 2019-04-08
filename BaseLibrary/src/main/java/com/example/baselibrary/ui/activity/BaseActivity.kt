package com.example.baselibrary.ui.activity

import android.os.Bundle
import com.example.baselibrary.common.AppManager
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

abstract class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setView())
        initView()
        initEvent(savedInstanceState)
        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }

//    val contentView: View get() = find<FrameLayout>(R.id.content).getChildAt(0)

    abstract fun setView(): Int
    abstract fun initView()
    open fun initEvent(savedInstanceState: Bundle?) {}

}