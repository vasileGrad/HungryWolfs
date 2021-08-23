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
        val divider = DividerItemDecoration(requireContext(), RecyclerView.HORIZONTAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider_details_tag)
            ?.let { divider.setDrawable(it) }
        binding.recyclerViewDetailsTags.addItemDecoration(divider)
        binding.recyclerViewDetailsTags.adapter = detailsAdapter
        viewModel.getMealWithDetails(args.idMeal)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.navigationToHome.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        viewModel.mealTags.observe(viewLifecycleOwner) { detailsAdapter.setMealTags(it) }
    }
}