package com.example.hungrywolfs.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hungrywolfs.Constants
import com.example.hungrywolfs.network.MealDetailsInfo
import com.orhanobut.hawk.Hawk

class FavoritesViewModel : ViewModel() {

    private val _favoriteMeals = MutableLiveData<MutableList<MealDetailsInfo>>()
    val favoriteMeals: LiveData<MutableList<MealDetailsInfo>> = _favoriteMeals

    init {
        getFavorites()
    }

    fun removeFavoriteMeal(favoriteMeal: MealDetailsInfo) {
        if (_favoriteMeals.value?.contains(favoriteMeal) == true) {
            _favoriteMeals.value = _favoriteMeals.value?.apply {
                this.remove(favoriteMeal)
                Hawk.put(Constants.FAVORITES, this)
            }
        }
    }

    fun getFavorites() {
        _favoriteMeals.value = Hawk.get<MutableList<MealDetailsInfo>>(Constants.FAVORITES) ?: mutableListOf()
    }
}