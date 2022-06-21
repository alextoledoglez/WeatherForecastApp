package com.personal.weatherforecastapp.data.entities

import com.google.gson.annotations.SerializedName

data class WeatherEntity(
    @SerializedName(value = "coord")
    val location: LocationDetails,

    @SerializedName(value = "weather")
    val weatherDetails: List<WeatherDetails>,

    @SerializedName(value = "main")
    val mainDetails: MainDetails,

    @SerializedName(value = "wind")
    val windDetails: WinDetails,

    @SerializedName(value = "clouds")
    val cloudsDetails: CloudsDetails,

    @SerializedName(value = "rain")
    val rainDetails: VolumeDetails,

    @SerializedName(value = "snow")
    val snowDetails: VolumeDetails,

    @SerializedName(value = "dt")
    val dt: Long,

    @SerializedName(value = "sys")
    val sysDetails: SysDetails,

    @SerializedName(value = "timezone")
    val timeZone: Long,

    @SerializedName(value = "name")
    val cityName: String,

    @SerializedName(value = "dt_txt")
    val dtTxt: String
)


