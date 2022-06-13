package com.bangkit2022.kulinerin.api.fetch

import com.bangkit2022.kulinerin.api.response.PredictResult
import com.bangkit2022.kulinerin.api.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST("predict")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part,
    ): PredictResult

    @POST("register")
    suspend fun register(
        @Body params: RequestBody
    ): RegisterResponse

    @POST("login")
    suspend fun login(
        @Body params: RequestBody
    ): RegisterResponse
}