package com.example.hungrywolfs.model

import android.util.Log
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolfs.R
import com.example.hungrywolfs.SingleLiveEvent
import com.example.hungrywolfs.network.User
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _navigationToHome = SingleLiveEvent<Any>()
    val navigationToHome = _navigationToHome

    private val _navigationToFavorites = SingleLiveEvent<Any>()
    val navigationToFavorites = _navigationToFavorites

    fun getUserDetails() {
        viewModelScope.launch {
            try {
                _user.value = User("Grad Vasile", "vasile.grad02@gmail.com", "+40 123 456 789")
            } catch (e: Exception) {
                Log.d("OrderViewModel: ", "Error: $e")
            }
        }
    }

    fun goToHomeFragment() {
        _navigationToHome.call()
    }

    fun goToFavoritesFragment() {
        _navigationToFavorites.call()
    }
}