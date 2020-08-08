package com.personal.weatherforecastapp.data.response.details

import com.google.gson.annotations.SerializedName

data class LocationDetails(
    @SerializedName(value = "lon")
    val lon: Double,
    @SerializedName(value = "lat")
    val lat: Double
)