package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.*
import com.example.hungrywolfs.Constants
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

    private var _favoriteMeals = mutableListOf<MealDetailsInfo>()

    val mealTags: LiveData<List<String>> = _mealDetails.map {
        it.strTags?.split(",") ?: listOf()
    }

    val isFavorite = MutableLiveData(false)

    init {
        _favoriteMeals = Hawk.get(Constants.FAVORITES)
    }

    fun getMealWithDetails(idMeal: String) {
        viewModelScope.launch {
            try {
                _mealDetails.value =
                    MealApi.retrofitService.getMealDetails(idMeal).meals.getOrNull(0)
                isFavorite.value = _favoriteMeals.contains(_mealDetails.value)
            } catch (e: Exception) {
                Log.d("DetailsViewModel: ", "Error: $e")
            }
        }
    }

    fun addToMealFavorites() {
        _favoriteMeals =
            Hawk.get<MutableList<MealDetailsInfo>>(Constants.FAVORITES) ?: mutableListOf()
        if (!_favoriteMeals.contains(_mealDetails.value)) {
            _mealDetails.value?.let {
                _favoriteMeals.add(it)
            }
        } else {
            _favoriteMeals.remove(_mealDetails.value)
        }
        Hawk.put(Constants.FAVORITES, _favoriteMeals)
    }

    fun goToHomeFragment() {
        _navigationToHome.call()
    }
}