package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.*
import com.example.hungrywolfs.SingleLiveEvent
import com.example.hungrywolfs.network.*
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _foodCategories = MutableLiveData<Category>()
    val foodCategories: LiveData<Category> = _foodCategories

    private val _meals = MutableLiveData<Meal>()
    val meals: LiveData<Meal> = _meals

    private val _navigationToSearch = SingleLiveEvent<Any>()
    val navigationToSearch = _navigationToSearch

    init {
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            try {
                MealApi.retrofitService.getMealCategories().let {
                    _foodCategories.value = it
                    it.categories.getOrNull(0)?.let {
                        getSelectedMeals(it)
                    }
                }
                Log.d("HomeViewModel: ", "Categories retrieved with success")
            } catch (e: Exception) {
                Log.d("HomeViewModel: ", "Error: $e")
            }
        }
    }

    fun getSelectedMeals(category: CategoryInfo) {
        viewModelScope.launch {
            try {
                _meals.value = MealApi.retrofitService.getMeals(category.strCategory)
                Log.d("HomeViewModel: ", "Meals retrieved with success")
            } catch (e: Exception) {
                Log.d("HomeViewModel: ", "Error: $e")
            }
        }
    }

    fun goToSearchFragment() {
        _navigationToSearch.call()
    }
}