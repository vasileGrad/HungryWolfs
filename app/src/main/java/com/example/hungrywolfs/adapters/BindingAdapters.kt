package com.example.hungrywolfs.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hungrywolfs.R
import com.example.hungrywolfs.network.MealInfo

@BindingAdapter("strMealThumb")
fun ImageView.bindCircleImage(imgUrl: String?) {
    imgUrl ?: return
    val imgUri = imgUrl.toUri()
    this.load(imgUri) {
        placeholder(R.drawable.loading_animation)
        error(R.drawable.ic_broken_image)
    }
}

@BindingAdapter("strMeal")
fun TextView.bindText(text: String?) {
    this.text = text
}

@BindingAdapter("listMeals")
fun RecyclerView.bindRecycler(listMeals: List<MealInfo>?) {
    val adapter = this.adapter as? MealGridAdapter
    adapter?.submitList(listMeals)
}