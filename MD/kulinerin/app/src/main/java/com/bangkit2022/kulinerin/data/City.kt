package com.bangkit2022.kulinerin.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    var name: String
): Parcelable
