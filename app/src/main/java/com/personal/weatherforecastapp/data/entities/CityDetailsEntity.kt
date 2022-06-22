package com.personal.weatherforecastapp.data.entities

import com.google.gson.annotations.SerializedName

data class CityDetailsEntity(
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "coord")
    val location: LocationDetailsEntity,
    @SerializedName(value = "country")
    val country: String,
    @SerializedName(value = "population")
    val population: Long,
    @SerializedName(value = "timezone")
    val timezone: Long,
    @SerializedName(value = "sunrise")
    val sunrise: Long,
    @SerializedName(value = "sunset")
    val sunset: Long
)