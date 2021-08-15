package com.example.hungrywolfs.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.hungrywolfs.adapters.CategoryAdapter
import com.example.hungrywolfs.adapters.MealAdapter
import com.example.hungrywolfs.databinding.FragmentHomeBinding
import com.example.hungrywolfs.model.AppViewModel

class HomeFragment : Fragment() {

    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.homeFragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter()
        val mealAdapter = MealAdapter()

        binding.recyclerViewCategories.adapter = categoryAdapter
        binding.recyclerViewMeals.adapter = mealAdapter

        viewModel.info.observe(viewLifecycleOwner) { categoryAdapter.setCategories(it.categories) }
        viewModel.mealInfo.observe(viewLifecycleOwner) { mealAdapter.setMeals(it.meals) }
        categoryAdapter.selected.observe(viewLifecycleOwner) { viewModel.getSelectedMeals(it) }
    }
}
