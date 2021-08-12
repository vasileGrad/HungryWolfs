package com.example.hungrywolfs

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.model.OrderViewModel
import com.example.hungrywolfs.network.CategoriesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class CategoryAdapter(private val viewModel: OrderViewModel) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    init {
        runBlocking {
            val job = launch {
                try {
                    viewModel.setMealInfo(CategoriesApi.retrofitService.getMealCategories())
                    Log.d("DEBUG CATEGORY API",
                        "Success API retrieve " + "\n${viewModel.mealInfo.value?.categories}")
                } catch (e: Exception) {
                    Log.d("DEBUG", "API retrieve error: $e")
                }
            }
            job.join()
        }
    }

    class CategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<TextView>(R.id.category_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.category_item_view, parent, false)

        return CategoryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = viewModel.mealInfo.value?.categories?.get(position)?.strCategory
        holder.button.text = item
        holder.button.setOnClickListener {
            val context = holder.view.context
            Log.i("CATEGORY: ", item.toString())
        }
    }

    override fun getItemCount(): Int = viewModel.mealInfo.value!!.categories.size
}