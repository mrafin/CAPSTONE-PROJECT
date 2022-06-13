package com.bangkit2022.kulinerin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit2022.kulinerin.api.fetch.ApiConfig
import com.bangkit2022.kulinerin.api.response.PredictResult
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class ResultCameraViewModel : ViewModel() {
    private val _errText = MutableLiveData<String>()
    val errText: LiveData<String> = _errText

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _result = MutableLiveData<PredictResult>()
    val result: LiveData<PredictResult> = _result

    fun uploadImage(img: MultipartBody.Part) {

        viewModelScope.launch {
            _isLoading.value = true

            try {
                val response = ApiConfig.getApiServiceForPredict().uploadImage(img)

                _isLoading.value = false
                _result.postValue(response)
            } catch (e: Exception) {
                _isLoading.value = false
                _errText.value = e.message.toString()
            }
        }
    }
}