package com.example.hungrywolfs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.hungrywolfs.fragments.FavoritesFragment
import com.example.hungrywolfs.fragments.HomeFragment
import com.example.hungrywolfs.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val favoritesFragment = FavoritesFragment()
        val settingsFragment = ProfileFragment()

        makeCurrentFragment(homeFragment)
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_wrapper, fragment)
            commit()
        }
}