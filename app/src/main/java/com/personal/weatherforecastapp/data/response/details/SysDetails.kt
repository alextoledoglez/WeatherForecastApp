package com.personal.weatherforecastapp.data.response.details

import com.google.gson.annotations.SerializedName

data class SysDetails(
    @SerializedName(value = "country")
    val country: String,
    @SerializedName(value = "sunrise")
    val sunrise: Long,
    @SerializedName(value = "sunset ")
    val sunset: Long
)