package com.example.goodscenter.iniection.module

import com.example.goodscenter.service.CategoryService
import com.example.goodscenter.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

@Module
class CategoryModule {

    @Provides
    fun providesCategoryService(service: CategoryServiceImpl): CategoryService = service

}