package com.personal.weatherforecastapp.data.entities

import com.google.gson.annotations.SerializedName

data class WindDetailsEntity(
    @SerializedName(value = "speed")
    val speed: Double,
    @SerializedName(value = "deg")
    val degrees: Int,
    @SerializedName(value = "gust")
    val gust: Double
)