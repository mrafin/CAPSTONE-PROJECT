package com.bangkit2022.kulinerin.ui.navigation.food

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    var name: String,
    var description: String,
    var image: Int,
): Parcelable
