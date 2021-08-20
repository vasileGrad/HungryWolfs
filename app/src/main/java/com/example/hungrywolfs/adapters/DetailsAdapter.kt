package com.example.hungrywolfs.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R
import com.example.hungrywolfs.network.MealDetailsInfo

class DetailsAdapter :
    RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {
    private val mealDetails: MutableList<MealDetailsInfo> = mutableListOf()

    class DetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mealTag: TextView = view.findViewById(R.id.meal_tag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.meal_tag_item_view, parent, false)
        return DetailsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.mealTag.text = mealDetails[position].strTags

        Log.d("DetailsAdapter", "strTags: ${mealDetails[position].strTags}")
    }

    override fun getItemCount(): Int {
        return mealDetails.size
    }

    fun setMealDetails(mealDetails: List<MealDetailsInfo>) {
        this.mealDetails.clear()
        this.mealDetails.addAll(mealDetails)
        notifyDataSetChanged()
    }
}