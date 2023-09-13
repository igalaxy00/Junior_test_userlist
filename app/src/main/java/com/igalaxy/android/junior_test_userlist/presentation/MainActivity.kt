package com.igalaxy.android.junior_test_userlist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.igalaxy.android.junior_test_userlist.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragmentManager: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragmentManager =
            (supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment)
        val childFragmentManager = navHostFragmentManager.childFragmentManager


        childFragmentManager.addOnBackStackChangedListener {
            if (childFragmentManager.backStackEntryCount == 0) {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            } else if (childFragmentManager.backStackEntryCount == 1) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        navHostFragmentManager.navController.navigateUp()
        return super.onSupportNavigateUp()
    }
}