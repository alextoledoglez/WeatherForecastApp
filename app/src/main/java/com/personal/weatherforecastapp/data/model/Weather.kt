package com.personal.weatherforecastapp.data.model

data class Weather(
    val id: Int,
    val imageSrc: String,
    val temperature: String,
    val location: String,
    val date: String,
    val description: String,
    val humidity: String,
    val win: String
)