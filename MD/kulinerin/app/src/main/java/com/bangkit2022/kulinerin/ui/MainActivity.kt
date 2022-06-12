package com.bangkit2022.kulinerin.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bangkit2022.kulinerin.R
import com.bangkit2022.kulinerin.data.User
import com.bangkit2022.kulinerin.databinding.ActivityMainBinding
import com.bangkit2022.kulinerin.ui.auth.UserPreference
import com.bangkit2022.kulinerin.ui.navigation.camera.CameraActivity
import com.bangkit2022.kulinerin.viewmodel.MainViewModel
import com.bangkit2022.kulinerin.viewmodel.MainViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()

        val navView: BottomNavigationView = binding.bottomNavigationView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_account
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.background = null

        binding.apply {
            fabCamera.setOnClickListener {
                val openCamera = Intent(this@MainActivity, CameraActivity::class.java)
                startActivity(openCamera)
            }
        }
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(UserPreference.getInstance(this))
        )[MainViewModel::class.java]

        mainViewModel.getUserSession().observe(this) {
            if (it.isLogin) {
                user = User(email = it.email, id = it.id)
            } else {
                val moveToBoarding = Intent(this, OnBoarding::class.java)
                startActivity(moveToBoarding)
                finish()
            }
        }
    }
}