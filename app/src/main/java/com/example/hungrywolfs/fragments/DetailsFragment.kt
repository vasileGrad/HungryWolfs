package com.example.hungrywolfs.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfs.R
import com.example.hungrywolfs.adapters.DetailsAdapter
import com.example.hungrywolfs.databinding.FragmentDetailsBinding
import com.example.hungrywolfs.model.DetailsViewModel

class DetailsFragment : Fragment() {
    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var binding: FragmentDetailsBinding
    private val detailsAdapter = DetailsAdapter()
    private lateinit var idMeal: String
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewDetailsTags.adapter = detailsAdapter

        val divider = DividerItemDecoration(requireContext(), RecyclerView.HORIZONTAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider_details_tag)
            ?.let { divider.setDrawable(it) }
        binding.recyclerViewDetailsTags.addItemDecoration(divider)

        idMeal = args.idMeal
        viewModel.getMealWithDetails(idMeal)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.navigationToHome.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        viewModel.navigationToFavorites.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_detailsFragment_to_favoritesFragment)
        }
        viewModel.mealDetails.observe(viewLifecycleOwner) { detailsAdapter.setMealDetails(it.meals) }
    }
}