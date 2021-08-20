package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfs.SingleLiveEvent
import com.example.hungrywolfs.network.MealApi
import com.example.hungrywolfs.network.MealDetail
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val _navigationToHome = SingleLiveEvent<Any>()
    val navigationToHome = _navigationToHome

    private val _navigationToFavorites = SingleLiveEvent<Any>()
    val navigationToFavorites = _navigationToFavorites

    private val _mealDetails = MutableLiveData<MealDetail>()
    val mealDetails: LiveData<MealDetail> = _mealDetails

    private val _mealTags = MutableLiveData<List<String>>()
    val mealTags: LiveData<List<String>> = _mealTags

    fun goToHomeFragment() {
        navigationToHome.call()
    }

    fun goToFavoritesFragment() {
        navigationToFavorites.call()
    }

    fun getMealWithDetails(idMeal: String) {
        viewModelScope.launch {
            try {
                _mealDetails.value = MealApi.retrofitService.getMealDetails(idMeal)
                splitTagMeals(_mealDetails.value?.meals?.get(0)?.strTags)
            } catch (e: Exception) {
                Log.d("OrderViewModel: ", "Error: $e")
            }
        }
    }

    fun splitTagMeals(tagString: String?) {
        _mealTags.value = tagString?.split(",")
    }
}