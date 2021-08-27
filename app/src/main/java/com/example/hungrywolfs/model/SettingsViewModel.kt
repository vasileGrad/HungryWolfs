package com.example.hungrywolfs.model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.hungrywolfs.SingleLiveEvent

class SettingsViewModel : ViewModel() {

    private val _navigationToSettings = SingleLiveEvent<Any>()
    val navigationToSettings = _navigationToSettings

    fun goToHomeFragment() {
        _navigationToSettings.call()
    }

    fun subtractFromFontSize() {
        Log.d("TAG", "subtractFromFontSize: ")
    }

    fun addToFontSize() {
        Log.d("TAG", "addToFontSize: ")
    }
}