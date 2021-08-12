package com.example.hungrywolfs.network

data class MealCategories(val categories: List<MealInfo>)

data class MealInfo(
    val idCategory: String,
    val strCategory: String,
)