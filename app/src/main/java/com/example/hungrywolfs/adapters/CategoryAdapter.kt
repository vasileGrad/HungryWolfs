package com.example.hungrywolfs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R
import com.example.hungrywolfs.network.CategoryInfo

class CategoryAdapter(private val clickListener: (category: CategoryInfo) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categories: MutableList<CategoryInfo> = mutableListOf()

    private var actualSelected = 0
    private var oldSelected = 0

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.category_name)
        val underlineLine: View = view.findViewById(R.id.category_underline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.category_item_view, parent, false)

        return CategoryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.textView.text = category.strCategory
        if (actualSelected == position) {
            holder.textView.setTextColor(
                ContextCompat.getColor(
                    holder.textView.context,
                    R.color.orange
                )
            )
            holder.underlineLine.visibility = View.VISIBLE
        } else {
            holder.textView.setTextColor(
                ContextCompat.getColor(
                    holder.textView.context,
                    R.color.darker_gray
                )
            )
            holder.underlineLine.visibility = View.INVISIBLE
        }
        holder.textView.setOnClickListener {
            oldSelected = actualSelected
            actualSelected = position
            notifyItemChanged(actualSelected)
            notifyItemChanged(oldSelected)
            clickListener(category)
        }
    }

    override fun getItemCount() = categories.size

    fun setCategories(categories: List<CategoryInfo>) {
        this.categories.clear()
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }
}