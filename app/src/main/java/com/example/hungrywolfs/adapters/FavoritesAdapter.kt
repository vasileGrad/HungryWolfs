package com.example.hungrywolfs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hungrywolfs.R
import com.example.hungrywolfs.enums.ClickEvents
import com.example.hungrywolfs.network.MealDetailsInfo
import de.hdodenhof.circleimageview.CircleImageView

class FavoritesAdapter(private val clickListener: (favoriteMeal: MealDetailsInfo, event: ClickEvents) -> Unit) :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    private val favoriteMeals: MutableList<MealDetailsInfo> = mutableListOf()

    class FavoritesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val favoriteImage: CircleImageView = view.findViewById(R.id.favorite_meal_image)
        val favoriteName: TextView = view.findViewById(R.id.favorite_meal_name)
        val favoritesConstraintLayout: ConstraintLayout =
            view.findViewById(R.id.favorites_constraint_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.favorite_item_view, parent, false)
        return FavoritesViewHolder(layout)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.favoriteName.text = favoriteMeals[position].strMeal
        val imageUri = favoriteMeals[position].strMealThumb
        imageUri.let {
            holder.favoriteImage.load(it.toUri()) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }
        holder.favoritesConstraintLayout.setOnLongClickListener {
            clickListener(favoriteMeals[position], ClickEvents.ON_LONG_CLICK)
            true
        }
        holder.favoritesConstraintLayout.setOnClickListener {
            clickListener(favoriteMeals[position], ClickEvents.ON_CLICK)
        }
    }

    override fun getItemCount(): Int {
        return favoriteMeals.size
    }

    fun setFavoriteMeals(favoriteMeals: List<MealDetailsInfo>) {
        this.favoriteMeals.clear()
        this.favoriteMeals.addAll(favoriteMeals)
        notifyDataSetChanged()
    }
}