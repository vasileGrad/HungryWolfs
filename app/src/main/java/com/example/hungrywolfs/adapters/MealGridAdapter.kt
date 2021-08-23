package com.example.hungrywolfs.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.databinding.GridViewItemBinding
import com.example.hungrywolfs.network.MealInfo

class MealGridAdapter :
    ListAdapter<MealInfo, MealGridAdapter.MealGridViewHolder>(DiffCallback) {

    class MealGridViewHolder(
        private var binding: GridViewItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mealInfo: MealInfo) {
            binding.mealInfo = mealInfo
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MealInfo>() {
        override fun areItemsTheSame(oldItem: MealInfo, newItem: MealInfo): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: MealInfo, newItem: MealInfo): Boolean {
            return oldItem.strMealThumb == newItem.strMealThumb
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealGridViewHolder {
        return MealGridViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: MealGridViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.mealConstraintLayout.setOnClickListener {
            clickListener(currentList[position].idMeal)
        }
    }
}