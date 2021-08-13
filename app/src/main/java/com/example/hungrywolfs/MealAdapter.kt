package com.example.hungrywolfs

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.model.OrderViewModel
import com.example.hungrywolfs.network.CategoriesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class MealAdapter(private val viewModel: OrderViewModel) :
    RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    init {
        runBlocking {
            val job = launch {
                try {
                    viewModel.setMealInfo(CategoriesApi.retrofitService.getMeals("Beef"))
                    Log.d("DEBUG CATEGORY API",
                        "Success API retrieve " + "\n${viewModel.mealInfo?.value?.meals.toString()}")
                } catch (e: Exception) {
                    Log.d("DEBUG", "API retrieve error: $e")
                }
            }
            job.join()
        }
    }

    class MealViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val mealName = view.findViewById<TextView>(R.id.meal_name)
        val mealImage = view.findViewById<ImageView>(R.id.meal_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.meal_item_view, parent, false)

        return MealViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val mealName = viewModel.mealInfo.value?.meals?.get(position)?.strMeal
        val mealImage = viewModel.mealInfo.value?.meals?.get(position)?.strMealThumb
        holder.mealName.text = mealName
    }

    override fun getItemCount() = viewModel.mealInfo.value!!.meals.size
}