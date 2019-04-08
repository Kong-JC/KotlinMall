package com.example.baselibrary.rx

import android.os.AsyncTask

class BaseException (val status: Int,val msg:String) :Throwable() {
}