package com.example.hungrywolfs.adapters

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
    private val mealTags: MutableList<String?> = mutableListOf()

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
        if (mealTags.size > 0) holder.mealTag.text = mealTags[position]
    }

    override fun getItemCount(): Int {
        return mealTags.size
    }

    fun setMealDetails(mealDetails: List<MealDetailsInfo>) {
        this.mealDetails.clear()
        this.mealDetails.addAll(mealDetails)
        notifyDataSetChanged()
    }

    fun setMealTags(mealTags: List<String>) {
        this.mealTags.clear()
        this.mealTags.addAll(mealTags)
        notifyDataSetChanged()
    }
}