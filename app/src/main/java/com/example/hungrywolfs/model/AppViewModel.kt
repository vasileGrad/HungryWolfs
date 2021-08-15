package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfs.network.MealApi
import com.example.hungrywolfs.network.Category
import com.example.hungrywolfs.network.Meal
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {
    private val _foodInfo = MutableLiveData<Category>()
    val info: LiveData<Category> = _foodInfo

    private val _mealInfo = MutableLiveData<Meal>()
    val mealInfo: LiveData<Meal> = _mealInfo

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            try {
                _foodInfo.value = MealApi.retrofit_service.getMealCategories()
                Log.d("OrderViewModel: ", "Categories retrieved with success")
            } catch (e: Exception) {
                Log.d("OrderViewModel: ", "Error: $e")
            }
        }
    }

    fun getSelectedMeals(categoryId: Int) {
        viewModelScope.launch {
            try {
                _mealInfo.value = info.value?.categories?.get(categoryId)?.let {
                    MealApi.retrofit_service.getMeals(
                        it.strCategory
                    )
                }
                Log.d("OrderViewModel: ", "Meals retrieved with success")
            } catch (e: Exception) {
                Log.d("OrderViewModel: ", "Error: $e")
            }
        }
    }
}