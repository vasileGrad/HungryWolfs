package com.example.hungrywolfs.model

import androidx.lifecycle.ViewModel
import com.example.hungrywolfs.SingleLiveEvent

class DetailsViewModel : ViewModel() {

    private val _navigationToSearch = SingleLiveEvent<Any>()
    val navigationToSearch = _navigationToSearch

    fun goToDetailsFragment() {
        navigationToSearch.call()
    }
}