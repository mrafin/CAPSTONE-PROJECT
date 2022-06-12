package com.bangkit2022.kulinerin.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.File

@Parcelize
data class Food(
    var name: String,
    var description: String,
    var image: Int,
    var recipe: String
): Parcelable
