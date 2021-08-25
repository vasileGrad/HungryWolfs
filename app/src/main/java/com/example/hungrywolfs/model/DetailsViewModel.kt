package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.*
import com.example.hungrywolfs.SingleLiveEvent
import com.example.hungrywolfs.network.MealApi
import com.example.hungrywolfs.network.MealDetailsInfo
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val _navigationToHome = SingleLiveEvent<Any>()
    val navigationToHome = _navigationToHome

    private var _mealDetails = MutableLiveData<MealDetailsInfo>()
    val mealDetails: LiveData<MealDetailsInfo> = _mealDetails

    val mealTags: LiveData<List<String>> = _mealDetails.map {
        it.strTags?.split(",") ?: listOf()
    }

    fun getMealWithDetails(idMeal: String) {
        viewModelScope.launch {
            try {
                _mealDetails.value =
                    MealApi.retrofitService.getMealDetails(idMeal).meals.getOrNull(0)
            } catch (e: Exception) {
                Log.d("OrderViewModel: ", "Error: $e")
            }
        }
    }

    fun goToHomeFragment() {
        navigationToHome.call()
    }

    fun addToMealFavorites() {
        _mealDetails.value = _mealDetails.value?.apply {
            this.isFavorite = !this.isFavorite
        }
    }
}