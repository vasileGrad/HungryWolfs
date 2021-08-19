package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfs.network.*
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {
    private val _foodCategories = MutableLiveData<Category>()
    val foodCategories: LiveData<Category> = _foodCategories

    private val _meals = MutableLiveData<Meal>()
    val meals: LiveData<Meal> = _meals

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            try {
                MealApi.retrofitService.getMealCategories()?.let {
                    _foodCategories.value = it
                    it.categories.getOrNull(0)?.let {
                        getSelectedMeals(it)
                    }
                }
                Log.d("OrderViewModel: ", "Categories retrieved with success")
            } catch (e: Exception) {
                Log.d("OrderViewModel: ", "Error: $e")
            }
        }
    }

    fun getSelectedMeals(category: CategoryInfo) {
        viewModelScope.launch {
            try {
                _meals.value = MealApi.retrofitService.getMeals(category.strCategory)
                Log.d("OrderViewModel: ", "Meals retrieved with success")
            } catch (e: Exception) {
                Log.d("OrderViewModel: ", "Error: $e")
            }
        }
    }
}