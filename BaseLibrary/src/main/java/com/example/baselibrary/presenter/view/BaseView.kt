package com.example.baselibrary.presenter.view

interface BaseView{
    fun showLoading()
    fun hideLoading()
    fun onError(text:String)
}