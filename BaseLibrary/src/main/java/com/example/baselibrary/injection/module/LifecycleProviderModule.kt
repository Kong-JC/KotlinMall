package com.example.baselibrary.injection.module

import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

@Module
class  LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>){
    @Provides
    fun providesLifecycleProvider(): LifecycleProvider<*> = lifecycleProvider
}