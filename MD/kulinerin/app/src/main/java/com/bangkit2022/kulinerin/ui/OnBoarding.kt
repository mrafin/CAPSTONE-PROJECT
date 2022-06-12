package com.bangkit2022.kulinerin.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.bangkit2022.kulinerin.R
import com.bangkit2022.kulinerin.databinding.ActivityOnboardingBinding
import com.bangkit2022.kulinerin.ui.auth.LoginActivity
import com.bangkit2022.kulinerin.ui.auth.RegisterActivity

class OnBoarding : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()

        binding.apply {
            btnLogIn.setOnClickListener(this@OnBoarding)
            btnSignUp.setOnClickListener(this@OnBoarding)
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_log_in -> {
                val mIntent = Intent(this, LoginActivity::class.java)
                startActivity(mIntent)
            }
            R.id.btn_sign_up -> {
                val mIntent = Intent(this, RegisterActivity::class.java)
                startActivity(mIntent)
            }
        }
    }
}