package com.example.hungrywolfs.model

import androidx.lifecycle.ViewModel
import com.example.hungrywolfs.SingleLiveEvent

class DetailsViewModel : ViewModel() {

    private val _navigationToHome = SingleLiveEvent<Any>()
    val navigationToHome = _navigationToHome

    private val _navigationToFavorites = SingleLiveEvent<Any>()
    val navigationToFavorites = _navigationToFavorites

    fun goToHomeFragment() {
        navigationToHome.call()
    }

    fun goToFavoritesFragment() {
        navigationToFavorites.call()
    }
}