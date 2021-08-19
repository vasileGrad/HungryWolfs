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
fun bindCircleImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("strMeal")
fun bindText(textView: TextView, text: String?) {
    textView.text = text
}

@BindingAdapter("listMeals")
fun RecyclerView.bindRecycler(listMeals: List<MealInfo>?) {
    val adapter = this.adapter as MealGridAdapter
    adapter.submitList(listMeals)
}