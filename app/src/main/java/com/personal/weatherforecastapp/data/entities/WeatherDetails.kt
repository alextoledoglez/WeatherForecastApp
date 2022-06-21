package com.personal.weatherforecastapp.data.entities

import com.google.gson.annotations.SerializedName

data class WeatherDetails(
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "main")
    val main: String,
    @SerializedName(value = "description")
    val description: String,
    @SerializedName(value = "icon")
    val iconCode: String
)