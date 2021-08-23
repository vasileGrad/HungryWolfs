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

    private val _mealTags = MutableLiveData<List<String>>()
    val mealTags: LiveData<List<String>> = Transformations.map(_mealTags) { it }

    fun getMealWithDetails(idMeal: String) {
        viewModelScope.launch {
            try {
                _mealDetails.value =
                    MealApi.retrofitService.getMealDetails(idMeal).meals.getOrNull(0)
                splitTagMeals(_mealDetails.value?.strTags)
            } catch (e: Exception) {
                Log.d("OrderViewModel: ", "Error: $e")
            }
        }
    }

    fun goToHomeFragment() {
        navigationToHome.call()
    }

    fun splitTagMeals(tagString: String?) {
        _mealTags.value = tagString?.split(",")
    }

    fun onClickIconFavorites() {
        _mealDetails.value = _mealDetails.value?.apply {
            this.isFavorite = !this.isFavorite
        }
    }
}