package com.example.hungrywolfs.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    fun setQuantity(number: Int) {
        _quantity.value = number
    }
}