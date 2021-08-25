package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.*
import com.example.hungrywolfs.SingleLiveEvent
import com.example.hungrywolfs.network.MealApi
import com.example.hungrywolfs.network.MealDetailsInfo
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val _navigationToHome = SingleLiveEvent<Any>()
    val navigationToHome = _navigationToHome

    private var _mealDetails = MutableLiveData<MealDetailsInfo>()
    val mealDetails: LiveData<MealDetailsInfo> = _mealDetails

    private var _favoriteMeals = MutableLiveData<MutableList<MealDetailsInfo>>()

    val mealTags: LiveData<List<String>> = _mealDetails.map {
        it.strTags?.split(",") ?: listOf()
    }

    val isFavorite = MutableLiveData(false)

    init {
        _favoriteMeals.value = Hawk.get<MutableList<MealDetailsInfo>>("favorites")
    }

    fun getMealWithDetails(idMeal: String) {
        viewModelScope.launch {
            try {
                _mealDetails.value =
                    MealApi.retrofitService.getMealDetails(idMeal).meals.getOrNull(0)
                if (_favoriteMeals.value?.contains(_mealDetails.value) == true) {
                    isFavorite.value = true
                }
            } catch (e: Exception) {
                Log.d("DetailsViewModel: ", "Error: $e")
            }
        }
    }

    fun goToHomeFragment() {
        navigationToHome.call()
    }

    fun addToMealFavorites() {
        _favoriteMeals.value =
            Hawk.get<MutableList<MealDetailsInfo>>("favorites") ?: mutableListOf()
        if (_favoriteMeals.value?.contains(_mealDetails.value) == false) {
            _mealDetails.value?.let {
                _favoriteMeals.value?.add(it)
            }
        } else {
            _favoriteMeals.value?.remove(_mealDetails.value)
        }
        Hawk.put<MutableList<MealDetailsInfo>>("favorites", _favoriteMeals.value)
    }
}