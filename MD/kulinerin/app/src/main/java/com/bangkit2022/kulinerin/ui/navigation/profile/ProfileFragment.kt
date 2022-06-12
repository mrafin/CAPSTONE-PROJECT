package com.bangkit2022.kulinerin.ui.navigation.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit2022.kulinerin.R
import com.bangkit2022.kulinerin.data.User
import com.bangkit2022.kulinerin.databinding.FragmentProfileBinding
import com.bangkit2022.kulinerin.helper.removeEmail
import com.bangkit2022.kulinerin.ui.OnBoarding
import com.bangkit2022.kulinerin.ui.auth.UserPreference
import com.bangkit2022.kulinerin.viewmodel.MainViewModel
import com.bangkit2022.kulinerin.viewmodel.MainViewModelFactory
import com.bumptech.glide.Glide

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileViewModel: MainViewModel
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()

        binding.apply {
            Glide
                .with(view.context)
                .load("https://st2.depositphotos.com/1006318/5909/v/450/depositphotos_59095493-stock-illustration-profile-icon-male-avatar.jpg")
                .into(profileImage)

            tvName.text = removeEmail(user.email.toString()).capitalize()
            btnLogOut.setOnClickListener{
                profileViewModel.logOut()
                logOut()
            }
        }
    }

    private fun setupViewModel() {
        profileViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(UserPreference.getInstance(requireActivity()))
        )[MainViewModel::class.java]

        profileViewModel.getUserSession().observe(requireActivity()) {
            user = User(email = it.email, id = it.id)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun logOut() {
        val mIntent = Intent(requireActivity(), OnBoarding::class.java)
        mIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(mIntent)
        onDestroy()
    }

}