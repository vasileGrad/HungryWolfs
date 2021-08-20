package com.example.hungrywolfs.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewDetailsTags.adapter = detailsAdapter

        idMeal = args.idMeal
        Log.d("idMeal", "idMeallll: $idMeal")
    }
}