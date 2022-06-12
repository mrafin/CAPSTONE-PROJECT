package com.bangkit2022.kulinerin.api.response

import com.google.gson.annotations.SerializedName

data class PredictResult(

    @field:SerializedName("predict")
    val predict: String,
)

data class RegisterResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("password")
    val password: String,
)