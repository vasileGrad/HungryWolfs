package com.example.hungrywolfs.network

data class MealDetail(val meals: List<MealDetailsInfo>)

data class MealDetailsInfo(
    val idMeal: String,
    val strMeal: String,
    val strInstructions: String?,
    val strMealThumb: String,
    val strTags: String?,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
)