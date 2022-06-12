package com.bangkit2022.kulinerin.ui.auth

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bangkit2022.kulinerin.R
import com.bangkit2022.kulinerin.data.User
import com.bangkit2022.kulinerin.databinding.ActivityLoginBinding
import com.bangkit2022.kulinerin.ui.MainActivity
import com.bangkit2022.kulinerin.viewmodel.LoginViewModel
import com.bangkit2022.kulinerin.viewmodel.LoginViewModelFactory

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)

        setupViewModel()

        binding.btnLogIn.setOnClickListener(this)
    }

    private fun setupViewModel() {
        loginViewModel = ViewModelProvider(
            this,
            LoginViewModelFactory(UserPreference.getInstance(this))
        )[LoginViewModel::class.java]

        loginViewModel.errText.observe(this) {
            Toast.makeText(
                this,
                it,
                Toast.LENGTH_LONG
            ).show()

            if (progressDialog.isShowing) {
                loadingDialog(false)
            }
        }

        loginViewModel.isLoading.observe(this, ::loadingDialog)
    }

    private fun moveToMain() {
        val mIntent = Intent(this, MainActivity::class.java)
        mIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(mIntent)
        finish()
    }

    private fun clearEditText() {
        binding.apply {
            edtEmail.text.clear()
            password.text.clear()
        }
    }

    private fun loadingDialog(isShow: Boolean) {
        progressDialog.setMessage(getString(R.string.prompt_loading))
        if (isShow) progressDialog.show() else progressDialog.dismiss()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_log_in -> {
                val email = binding.edtEmail.text.toString()
                val password = binding.password.text.toString()

                when {
                    email.isEmpty() -> binding.edtEmail.error =
                        getString(R.string.email_null)
                    password.isEmpty() -> binding.edtPassword.error =
                        getString(R.string.password_null)
                    else -> {
                        loginViewModel.userLogin(email, password).observe(this) {
                            val user = User(
                                id = it.id,
                                email = it.email,
                                isLogin = true
                            )

                            loginViewModel.saveUser(user)
                            moveToMain()
                        }
                        clearEditText()
                    }
                }
            }
        }
    }
}