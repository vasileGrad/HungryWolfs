package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.*
import com.example.hungrywolfs.SingleLiveEvent
import com.example.hungrywolfs.network.MealApi
import com.example.hungrywolfs.network.MealInfo
import kotlinx.coroutines.delay

private const val SEARCH_DEBOUNCE = 500L

class SearchViewModel : ViewModel() {

    val query = MutableLiveData<String>("")

    private val _navigationToHome = SingleLiveEvent<Any>()
    val navigationToHome = _navigationToHome

    // Gabi provided this code
    val searchedMeals: LiveData<List<MealInfo>> = query.switchMap {
        liveData(viewModelScope.coroutineContext) {
            delay(SEARCH_DEBOUNCE)
            try {
                emit(MealApi.retrofitService.getSearchedMeals(it).meals)
                Log.d("OrderViewModel: ", "Searched meals retrieved with success")
            } catch (e: Exception) {
                Log.d("OrderViewModel: ", "Error: $e")
                emit(listOf<MealInfo>())
            }
        }
    }

    fun splitMealTags() {

    }

    fun goToHomeFragment() {
        _navigationToHome.call()
    }
}