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
import com.example.hungrywolfs.adapters.CategoryAdapter
import com.example.hungrywolfs.adapters.MealAdapter
import com.example.hungrywolfs.databinding.FragmentHomeBinding
import com.example.hungrywolfs.model.HomeViewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private val categoryAdapter =
        CategoryAdapter { category -> viewModel.getSelectedMeals(category) }
    private val mealAdapter = MealAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val divider = DividerItemDecoration(requireContext(), RecyclerView.HORIZONTAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)
            ?.let { divider.setDrawable(it) }
        binding.recyclerViewMeals.addItemDecoration(divider)
        binding.recyclerViewCategories.adapter = categoryAdapter
        binding.recyclerViewMeals.adapter = mealAdapter
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.navigateToSearch.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
        viewModel.foodCategories.observe(viewLifecycleOwner) { categoryAdapter.setCategories(it.categories) }
        viewModel.meals.observe(viewLifecycleOwner) {
            mealAdapter.setMeals(it.meals)
            binding.recyclerViewMeals.scrollToPosition(0)
        }
    }
}
