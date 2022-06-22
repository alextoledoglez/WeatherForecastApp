package com.personal.weatherforecastapp.data.entities

import com.google.gson.annotations.SerializedName

data class WeatherEntity(
    @SerializedName(value = "coord")
    val location: LocationDetailsEntity,

    @SerializedName(value = "weather")
    val weatherDetailEntities: List<WeatherDetailsEntity>,

    @SerializedName(value = "main")
    val mainDetailsEntity: MainDetailsEntity,

    @SerializedName(value = "wind")
    val windDetails: WindDetailsEntity,

    @SerializedName(value = "clouds")
    val cloudsDetailsEntity: CloudsDetailsEntity,

    @SerializedName(value = "rain")
    val rainDetails: VolumeDetailsEntity,

    @SerializedName(value = "snow")
    val snowDetails: VolumeDetailsEntity,

    @SerializedName(value = "dt")
    val dt: Long,

    @SerializedName(value = "sys")
    val sysDetailsEntity: SysDetailsEntity,

    @SerializedName(value = "timezone")
    val timeZone: Long,

    @SerializedName(value = "name")
    val cityName: String,

    @SerializedName(value = "dt_txt")
    val dtTxt: String
)


