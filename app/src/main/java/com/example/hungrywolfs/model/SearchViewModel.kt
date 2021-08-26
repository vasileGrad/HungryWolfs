package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.*
import com.example.hungrywolfs.SingleLiveEvent
import com.example.hungrywolfs.network.InternetConnection
import com.example.hungrywolfs.network.MealApi
import com.example.hungrywolfs.network.MealInfo
import kotlinx.coroutines.delay

private const val SEARCH_DEBOUNCE = 300L

class SearchViewModel : ViewModel() {

    val query = MutableLiveData("")

    private val _navigationToHome = SingleLiveEvent<Any>()
    val navigationToHome = _navigationToHome

    private val _navigationToInternetFragment = SingleLiveEvent<Any>()
    val navigationToInternetFragment = _navigationToInternetFragment

    // Gabi provided this code
    val searchedMeals: LiveData<List<MealInfo>> = query.switchMap {
        liveData(viewModelScope.coroutineContext) {
            delay(SEARCH_DEBOUNCE)
            try {
                emit(MealApi.retrofitService.getSearchedMeals(it).meals)
                Log.d("OrderViewModel: ", "Searched meals retrieved with success")
            } catch (e: Exception) {
                checkInternetConnection()
                Log.d("OrderViewModel: ", "Error: $e")
                emit(listOf<MealInfo>())
            }
        }
    }

    private fun checkInternetConnection() {
        if (!InternetConnection.DoesNetworkHaveInternet.execute()) {
            Log.d("SearchViewModel:", "No Internet connection!: ")
            goToInternetFragment()
        }
    }

    fun goToHomeFragment() {
        _navigationToHome.call()
    }

    private fun goToInternetFragment() {
        _navigationToInternetFragment.call()
    }
}