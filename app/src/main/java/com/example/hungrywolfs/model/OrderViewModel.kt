package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfs.network.CategoriesApi
import com.example.hungrywolfs.network.FoodCategories
import com.example.hungrywolfs.network.Meals
import kotlinx.coroutines.launch

enum class CategoriesApiStatus { LOADING, ERROR, DONE }

class OrderViewModel : ViewModel() {
    private val _status = MutableLiveData<CategoriesApiStatus>()
    val status: LiveData<CategoriesApiStatus> = _status

    private val _foodInfo = MutableLiveData<FoodCategories>()
    val foodInfo: LiveData<FoodCategories> = _foodInfo

    private val _mealInfo = MutableLiveData<Meals>()
    val mealInfo: LiveData<Meals> = _mealInfo

    init {
        getCategories()
    }

    fun setFoodInfo(foodInfo: FoodCategories) {
        _foodInfo.value = foodInfo
    }

    fun setMealInfo(mealInfo: Meals) {
        _mealInfo.value = mealInfo
    }

    private fun getCategories() {
        viewModelScope.launch {
            _status.value = CategoriesApiStatus.LOADING
            try {
                val listResult = CategoriesApi.retrofitService.getFoodCategories()
                Log.d("OrderViewModel: ", "Categories:")
                _status.value = CategoriesApiStatus.DONE
            } catch (e: Exception) {
                _status.value = CategoriesApiStatus.ERROR
            }
        }
    }
}