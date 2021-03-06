package com.example.hungrywolfs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.hungrywolfs.databinding.ActivityMainBinding
import com.example.hungrywolfs.network.InternetConnection
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var internetConnection: InternetConnection

    private val destinationChangedListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            val bottomNavVisibility = when (destination.id) {
                R.id.homeFragment, R.id.favoritesFragment, R.id.profileFragment -> true
                else -> false
            }
            binding.bottomNavigation.visibility =
                if (bottomNavVisibility) View.VISIBLE else View.GONE
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener(destinationChangedListener)
        bottomNavigation.setupWithNavController(navController)
        checkNetworkConnection()
    }

    private fun checkNetworkConnection() {
        internetConnection = InternetConnection(application)
        internetConnection.observe(this, { isConnected ->
            if (isConnected) {
                if (findNavController(R.id.nav_host_fragment).currentBackStackEntry?.destination?.id == R.id.internetFragment)
                    findNavController(R.id.nav_host_fragment).popBackStack()
            } else {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_internetFragment)
            }
        })
    }
}