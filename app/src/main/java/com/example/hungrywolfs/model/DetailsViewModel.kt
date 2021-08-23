package com.example.hungrywolfs.model

import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfs.R
import com.example.hungrywolfs.SingleLiveEvent
import com.example.hungrywolfs.network.MealApi
import com.example.hungrywolfs.network.MealDetail
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val _navigationToHome = SingleLiveEvent<Any>()
    val navigationToHome = _navigationToHome

    private val _mealDetails = MutableLiveData<MealDetail>()
    val mealDetails: LiveData<MealDetail> = _mealDetails

    private val _mealTags = MutableLiveData<List<String>>()
    val mealTags: LiveData<List<String>> = _mealTags

    private var isActiveFavoritesIcon = false

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

    fun goToHomeFragment() {
        navigationToHome.call()
    }

    fun splitTagMeals(tagString: String?) {
        _mealTags.value = tagString?.split(",")
    }

    fun addToMealFavorites(view: View) {
        val iconFavorites: ImageButton = view.findViewById(R.id.icon_favorites)
        if (!isActiveFavoritesIcon) {
            iconFavorites.setImageResource(R.drawable.ic_favorites_active)
        } else {
            iconFavorites.setImageResource(R.drawable.ic_favorites_inactive)
        }
        isActiveFavoritesIcon = !isActiveFavoritesIcon
    }
}