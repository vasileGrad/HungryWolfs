package com.example.hungrywolfs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hungrywolfs.R
import com.example.hungrywolfs.network.MealInfo

class MealAdapter :
    RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
    private val meals: MutableList<MealInfo> = mutableListOf()

    class MealViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mealName: TextView = view.findViewById(R.id.meal_name)
        val mealImage: ImageView = view.findViewById(R.id.meal_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.meal_item_view, parent, false)
        return MealViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.mealName.text = meals[position].strMeal
        val imageUri = meals[position].strMealThumb
        imageUri.let {
            holder.mealImage.load(it.toUri()) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }
    }

    override fun getItemCount() = meals.size

    fun setMeals(meals: List<MealInfo>) {
        this.meals.clear()
        this.meals.addAll(meals)
        notifyDataSetChanged()
    }
}