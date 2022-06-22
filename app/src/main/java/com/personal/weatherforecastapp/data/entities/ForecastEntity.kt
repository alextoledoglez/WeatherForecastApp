package com.personal.weatherforecastapp.data.entities

import com.google.gson.annotations.SerializedName

data class ForecastEntity(
    @SerializedName(value = "list")
    val list: List<WeatherEntity>,
    @SerializedName(value = "city")
    val cityDetailsEntity: CityDetailsEntity
)