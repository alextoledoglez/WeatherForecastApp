package com.personal.weatherforecastapp.data.mappers

import com.personal.weatherforecastapp.data.entities.CityDetails
import com.personal.weatherforecastapp.data.entities.WeatherEntity
import com.personal.weatherforecastapp.domain.model.WeatherModel
import com.personal.weatherforecastapp.extensions.*

fun WeatherEntity.toModel(cityDetails: CityDetails) = WeatherModel(
    id = weatherDetails[0].id,
    imageSrc = weatherDetails[0].iconCode.getImageSrc(),
    temperature = mainDetails.temp.toCelsiusDegreesTemperatureStr(),
    location = cityDetails.toStrLocation(),
    date = dtTxt.toFormattedStrDate(),
    description = weatherDetails[0].description,
    humidity = mainDetails.humidity.toFormattedHumidity(),
    wind = windDetails.speed.toKmPerHourSpeedStr()
)