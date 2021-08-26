package com.example.hungrywolfs.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R
import com.example.hungrywolfs.adapters.MealGridAdapter
import com.example.hungrywolfs.databinding.FragmentSearchBinding
import com.example.hungrywolfs.model.SearchViewModel

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding
    private val mealGridAdapter = MealGridAdapter { idMeal -> navigateToDetails(idMeal) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dividerSearchHorizontal =
            DividerItemDecoration(requireContext(), RecyclerView.HORIZONTAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider_search)
            ?.let { dividerSearchHorizontal.setDrawable(it) }
        binding.recyclerViewSearchMeals.addItemDecoration(dividerSearchHorizontal)

        val dividerSearchVertical = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider_search)
            ?.let { dividerSearchVertical.setDrawable(it) }
        binding.recyclerViewSearchMeals.addItemDecoration(dividerSearchVertical)
        binding.recyclerViewSearchMeals.adapter = mealGridAdapter
        binding.searchMeals.isFocusableInTouchMode
        binding.searchMeals.isFocusable
        binding.searchMeals.requestFocus()
        setupObservers()
        showKeyboard()
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

    private fun checkSearchResults() {
        if (viewModel.searchedMeals.value?.size == 0) {
            binding.itemNotFoundRelativeLayout.visibility = View.VISIBLE
            binding.searchRelativeLayout.visibility = View.INVISIBLE
        } else {
            binding.itemNotFoundRelativeLayout.visibility = View.INVISIBLE
            binding.searchRelativeLayout.visibility = View.VISIBLE
        }
    }

    private fun showKeyboard() {
        val inputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(binding.searchMeals,
            InputMethodManager.SHOW_FORCED or InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }

    private fun setupObservers() {
        viewModel.navigationToHome.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        viewModel.searchedMeals.observe(viewLifecycleOwner) {
            checkSearchResults()
        }
    }

    private fun navigateToDetails(idMeal: String) {
        findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailsFragment(
            idMeal))
    }
}