package com.example.hungrywolfs.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R
import com.example.hungrywolfs.adapters.FavoritesAdapter
import com.example.hungrywolfs.databinding.FragmentFavoritesBinding
import com.example.hungrywolfs.enums.ClickEvents
import com.example.hungrywolfs.model.FavoritesViewModel

class FavoritesFragment : Fragment() {

    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var binding: FragmentFavoritesBinding
    private val favoritesAdapter =
        FavoritesAdapter { favoriteMeal, event ->
            if (event == ClickEvents.ON_LONG_CLICK) {
                viewModel.removeFavoriteMeal(favoriteMeal)
            } else {
                navigateToDetails(favoriteMeal.idMeal)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dividerFavoritesVertical =
            DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider_favorites)
            ?.let { dividerFavoritesVertical.setDrawable(it) }
        binding.recyclerViewFavorites.addItemDecoration(dividerFavoritesVertical)
        binding.recyclerViewFavorites.adapter = favoritesAdapter
        setupObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavorites()
    }

    private fun setupObservers() {
        viewModel.favoriteMeals.observe(viewLifecycleOwner) {
            favoritesAdapter.setFavoriteMeals(it)
        }
    }

    private fun navigateToDetails(favoriteMealId: String) {
        findNavController().navigate(FavoritesFragmentDirections.actionFavoritesFragmentToDetailsFragment(
            favoriteMealId))
    }
}