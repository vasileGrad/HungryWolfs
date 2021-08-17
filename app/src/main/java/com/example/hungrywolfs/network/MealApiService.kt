package com.example.hungrywolfs.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL =
    "https://www.themealdb.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MealApiService {

    @GET("/api/json/v1/1/categories.php")
    suspend fun getMealCategories(): Category

    @GET("/api/json/v1/1/filter.php")
    suspend fun getMeals(@Query("c") c: String): Meal
}

object MealApi {
    val retrofitService: MealApiService by lazy {
        retrofit.create(MealApiService::class.java)
    }
}