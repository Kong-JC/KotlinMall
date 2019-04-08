package com.example.baselibrary.injection.module

import android.app.Activity
//import android.content.Context
//import com.example.baselibrary.common.BaseApplication
import dagger.Module
import dagger.Provides
//import java.security.AccessControlContext
//import javax.inject.Singleton

@Module
class  ActivityModule(private val activity: Activity){
    @Provides
//    @Singleton
    fun providesActivity(): Activity = activity
}