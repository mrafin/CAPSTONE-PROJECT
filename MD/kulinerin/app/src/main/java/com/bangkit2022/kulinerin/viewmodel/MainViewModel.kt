package com.bangkit2022.kulinerin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit2022.kulinerin.data.User
import com.bangkit2022.kulinerin.ui.auth.UserPreference

class MainViewModel(private val pref: UserPreference) : ViewModel() {

    fun getUserSession(): LiveData<User> {
        val user = MutableLiveData<User>()
        user.value = pref.getUser()
        return user
    }

    fun logOut() = pref.clearUser()
}

class MainViewModelFactory(private val pref: UserPreference) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}