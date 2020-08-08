package com.personal.weatherforecastapp.data.response

import com.google.gson.annotations.SerializedName
import com.personal.weatherforecastapp.data.response.details.CityDetails

data class ForecastBodyResponse(
    @SerializedName(value = "list")
    val list: List<WeatherBodyResponse>,
    @SerializedName(value = "city")
    val cityDetails: CityDetails
)