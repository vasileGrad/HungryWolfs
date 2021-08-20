package com.example.hungrywolfs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R

class DetailsAdapter :
    RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {

    class DetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.meal_item_view, parent, false)
        return DetailsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}