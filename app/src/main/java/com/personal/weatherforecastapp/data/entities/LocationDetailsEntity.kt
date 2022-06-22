package com.personal.weatherforecastapp.data.entities

import com.google.gson.annotations.SerializedName

data class LocationDetailsEntity(
    @SerializedName(value = "lon")
    val lon: Double,
    @SerializedName(value = "lat")
    val lat: Double
)