package com.example.hungrywolfs.network

data class FoodCategories(val categories: List<FoodInfo>)

data class FoodInfo(
    val idCategory: String,
    val strCategory: String,
)

data class Meals(val meals: List<MealInfo>)

data class MealInfo(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
)