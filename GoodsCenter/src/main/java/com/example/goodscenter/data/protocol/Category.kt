package com.example.goodscenter.data.protocol

data class Category(
        val id: Int, // 分类ID
        val categoryName: String, // 分类名称
        val categoryIcon: String = "", // 分类图标
        val parentId: Int, // 分类 父类Id
        var isSelected: Boolean = false // 是否被选中
)