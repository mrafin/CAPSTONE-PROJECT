package com.bangkit2022.kulinerin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit2022.kulinerin.api.fetch.ApiConfig
import com.bangkit2022.kulinerin.api.response.RegisterResponse
import com.bangkit2022.kulinerin.helper.createJsonRequestBody
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errText = MutableLiveData<String>()
    val errText: LiveData<String> = _errText

    fun registerUser(email: String, password: String): LiveData<RegisterResponse> {

        val result = MutableLiveData<RegisterResponse>()

        viewModelScope.launch {
            _isLoading.value = true

            try {
                val response = ApiConfig.getApiServiceForAll().register(
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
}