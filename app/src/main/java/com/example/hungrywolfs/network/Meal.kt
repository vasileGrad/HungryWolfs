package com.example.hungrywolfs.network

data class Meal(val meals: List<MealInfo>)

data class MealInfo(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
)