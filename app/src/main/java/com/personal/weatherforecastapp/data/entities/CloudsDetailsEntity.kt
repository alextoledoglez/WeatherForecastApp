package com.personal.weatherforecastapp.data.entities

import com.google.gson.annotations.SerializedName

data class CloudsDetailsEntity(
    @SerializedName(value = "all")
    val percent: Int
)