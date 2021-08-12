package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfs.network.CategoriesApi
import com.example.hungrywolfs.network.MealCategories
import kotlinx.coroutines.launch

enum class CategoriesApiStatus { LOADING, ERROR, DONE }

class OrderViewModel : ViewModel() {
    private val _status = MutableLiveData<CategoriesApiStatus>()
    val status: LiveData<CategoriesApiStatus> = _status

    private val _mealInfo = MutableLiveData<MealCategories>()
    val mealInfo: LiveData<MealCategories> = _mealInfo

    init {
        getCategories()
    }

    fun setMealInfo(mealInfo: MealCategories) {
        _mealInfo.value = mealInfo
    }

    private fun getCategories() {
        viewModelScope.launch {
            _status.value = CategoriesApiStatus.LOADING
            try {
                val listResult = CategoriesApi.retrofitService.getMealCategories()
                Log.d("OrderViewModel: ", "Categories:")
                _status.value = CategoriesApiStatus.DONE
            } catch (e: Exception) {
                _status.value = CategoriesApiStatus.ERROR
            }
        }
    }
}