package com.bangkit2022.kulinerin.ui.auth

import android.app.ProgressDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bangkit2022.kulinerin.R
import com.bangkit2022.kulinerin.databinding.ActivityRegisterBinding
import com.bangkit2022.kulinerin.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        progressDialog = ProgressDialog(this)

        setupViewModel()
        setUpAction()
    }

    private fun setupViewModel() {
        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        registerViewModel.errText.observe(this) {
            Toast.makeText(
                this,
                it,
                Toast.LENGTH_LONG
            ).show()

            if (progressDialog.isShowing) {
                loadingDialog(false)
            }
        }

        registerViewModel.isLoading.observe(this, ::loadingDialog)
    }

    private fun setUpAction() {
        binding.btnSignUp.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.password.text.toString()
            val passwordVal = binding.passwordVal.text.toString()

            when {
                email.isEmpty() -> binding.edtEmail.error =
                    getString(R.string.email_null)
                password.isEmpty() -> binding.password.error =
                    getString(R.string.password_null)
                passwordVal.isEmpty() -> binding.password.error =
                    getString(R.string.passwordVal_null)
                passwordVal != password -> binding.passwordVal.error =
                    getString(R.string.passwordVal_not_match)
                else -> {
                    registerViewModel.registerUser(email, password).observe(this) {
                        Toast.makeText(
                            this,
                            "${it.email} created",
                            Toast.LENGTH_LONG
                        ).show()

                        finish()
                    }

                    clearEditText()
                }
            }
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

    private fun clearEditText() {
        binding.apply {
            edtEmail.text.clear()
            password.text.clear()
            passwordVal.text.clear()
        }
    }

    private fun loadingDialog(isShow: Boolean) {
        progressDialog.setMessage(getString(R.string.prompt_loading))
        if (isShow) progressDialog.show() else progressDialog.dismiss()
    }
}