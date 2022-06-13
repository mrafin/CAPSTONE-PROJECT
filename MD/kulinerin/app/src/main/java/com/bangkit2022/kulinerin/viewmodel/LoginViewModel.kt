package com.bangkit2022.kulinerin.viewmodel

import androidx.lifecycle.*
import com.bangkit2022.kulinerin.api.fetch.ApiConfig
import com.bangkit2022.kulinerin.api.response.RegisterResponse
import com.bangkit2022.kulinerin.data.User
import com.bangkit2022.kulinerin.helper.createJsonRequestBody
import com.bangkit2022.kulinerin.ui.auth.UserPreference
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: UserPreference) : ViewModel() {

    private val _errText = MutableLiveData<String>()
    val errText: LiveData<String> = _errText

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun userLogin(email: String, password: String): LiveData<RegisterResponse> {

        val result = MutableLiveData<RegisterResponse>()

        viewModelScope.launch {
            _isLoading.value = true

            try {
                val response = ApiConfig.getApiServiceForAll().login(
                    createJsonRequestBody(
                        "email" to email, "password" to password
                    )
                )

                _isLoading.value = false
                result.postValue(response)
            } catch (e: Exception) {
                _isLoading.value = false
                _errText.value = e.message.toString()
            }
        }

        return result
    }

    fun saveUser(user: User) {
        pref.saveUser(user)
    }
}

class LoginViewModelFactory(private val pref: UserPreference) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}