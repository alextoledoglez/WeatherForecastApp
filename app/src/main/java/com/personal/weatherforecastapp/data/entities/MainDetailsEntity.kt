package com.personal.weatherforecastapp.data.entities

import com.google.gson.annotations.SerializedName

data class MainDetailsEntity(
    @SerializedName(value = "temp")
    val temp: Double,
    @SerializedName(value = "feels_like")
    val feelsLike: Double,
    @SerializedName(value = "pressure")
    val pressure: Double,
    @SerializedName(value = "humidity")
    val humidity: Int,
    @SerializedName(value = "temp_min ")
    val tempMin: Double,
    @SerializedName(value = "temp_max")
    val tempMax: Double,
    @SerializedName(value = "sea_level")
    val seaLevel: Double,
    @SerializedName(value = "grnd_level")
    val groundLevel: Double
)