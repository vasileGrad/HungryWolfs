package com.example.hungrywolfs.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hungrywolfs.R
import com.example.hungrywolfs.databinding.FragmentProfileBinding
import com.example.hungrywolfs.model.ProfileViewModel

class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.termsAndConditionsIconBackRight.setOnClickListener {
            val queryUrl: Uri = Uri.parse("$URI")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            context?.startActivity(intent)
        }
        viewModel.getUserDetails()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.navigationToHome.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        viewModel.navigationToFavorites.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_profileFragment_to_favoritesFragment)
        }
        viewModel.navigationToSettings.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
        }
    }

    companion object {
        const val URI = "https://www.wolfpack-digital.com/privacy"
    }
}