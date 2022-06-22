package com.personal.weatherforecastapp.data.entities

import com.google.gson.annotations.SerializedName

data class VolumeDetailsEntity(
    @SerializedName(value = "1h")
    val lastHourVolume: Double,
    @SerializedName(value = "3h")
    val lastThreeHoursVolume: Double
)