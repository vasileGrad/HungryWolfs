package com.example.hungrywolfs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categories: List<String> = listOf("Foods", "Drinks", "Snacks", "Sauces")

    class CategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.category_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.category_item_view, parent, false)

        return CategoryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = categories[position]
        holder.textView.text = item
    }

    override fun getItemCount(): Int = categories.size
}