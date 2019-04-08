package com.example.goodscenter.iniection.component

import com.example.baselibrary.injection.PresenterComponentScope
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.goodscenter.iniection.module.CategoryModule
import com.example.goodscenter.ui.fragment.CategoryFragment
import dagger.Component

@PresenterComponentScope
@Component(dependencies = [ActivityComponent::class],modules = arrayOf(CategoryModule::class))
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)
}