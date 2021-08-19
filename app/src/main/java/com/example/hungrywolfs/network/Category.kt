package com.example.hungrywolfs.network

data class Category(val categories: List<CategoryInfo>)

data class CategoryInfo(
    val idCategory: String,
    val strCategory: String,
)