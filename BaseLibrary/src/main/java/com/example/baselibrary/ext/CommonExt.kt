package com.example.baselibrary.ext

import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.baselibrary.R
import com.example.baselibrary.data.protocol.BaseResponse
import com.example.baselibrary.rx.BaseFunc
import com.example.baselibrary.rx.BaseFuncBoolean
import com.example.baselibrary.rx.BaseSubscriber
import com.kennyc.view.MultiStateView
import com.kotlin.base.utils.GlideUtils
import com.kotlin.base.widgets.DefaultTextWatcher
import com.trello.rxlifecycle.LifecycleProvider
import org.jetbrains.anko.find
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

fun <T> Observable<T>.execute(
        subscriber: BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}

fun <T> Observable<BaseResponse<T>>.convert(): Observable<T> = this.flatMap(BaseFunc())

fun <T> Observable<BaseResponse<T>>.convertBoolean(): Observable<Boolean> = this.flatMap(BaseFuncBoolean())

fun View.onClick(listener: View.OnClickListener) = this.setOnClickListener(listener)

fun View.onClick(method: () -> Unit) = this.setOnClickListener { method() }

//fun Button.enabled(etArray: Array<EditText>, method: () -> Boolean) {
//    val btn = this
//    for (et in etArray) et.addTextChangedListener(object : DefaultTextWatcher() {
//        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            btn.isEnabled = method()
//        }
//    })
//}

// 判断是否启用按钮
fun Button.enabled(etArray: Array<EditText>) {
    isEnabled = false
    val btn = this
    for (et in etArray) et.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = returnNotEmptyCount(etArray) == etArray.size
        }
    })
}

// 返回不为空的次数
private fun returnNotEmptyCount(etArray: Array<EditText>): Int {
    var isNotEmptyCount = 0
    for (et in etArray) if (et.text.isNotEmpty()) isNotEmptyCount++ else isNotEmptyCount--
    return isNotEmptyCount
}

fun ImageView.loadUrl(url: String) {
    GlideUtils.loadUrlImage(context, url, this)
}

// 多状态视图开始加载
fun MultiStateView.startLoading() {
    viewState = MultiStateView.VIEW_STATE_LOADING
    val loadingView = getView(MultiStateView.VIEW_STATE_LOADING)
    val animBackground = loadingView!!.find<View>(R.id.loading_anim_view).background
    (animBackground as AnimationDrawable).start()
}

fun View.setVisibility(visibility: Boolean) {
    this.visibility = if (visibility) View.VISIBLE else View.GONE
}

fun showView(vArray: Array<View>) = setVisibility(true, vArray)

fun hideView(vArray: Array<View>) = setVisibility(false, vArray)

private fun setVisibility(visibility: Boolean, vArray: Array<View>) {
    for (v in vArray) v.visibility = if (visibility) View.VISIBLE else View.GONE
}

