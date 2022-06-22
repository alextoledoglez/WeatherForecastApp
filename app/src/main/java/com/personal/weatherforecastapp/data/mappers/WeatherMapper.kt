package com.personal.weatherforecastapp.data.mappers

import com.personal.weatherforecastapp.data.entities.CityDetailsEntity
import com.personal.weatherforecastapp.data.entities.WeatherEntity
import com.personal.weatherforecastapp.domain.model.WeatherModel
import com.personal.weatherforecastapp.extensions.*

fun WeatherEntity.toModel(cityDetailsEntity: CityDetailsEntity) = WeatherModel(
    id = weatherDetailEntities[0].id,
    imageSrc = weatherDetailEntities[0].iconCode.getImageSrc(),
    temperature = mainDetailsEntity.temp.toCelsiusDegreesTemperatureStr(),
    location = cityDetailsEntity.toStrLocation(),
    date = dtTxt.toFormattedStrDate(),
    description = weatherDetailEntities[0].description,
    humidity = mainDetailsEntity.humidity.toFormattedHumidity(),
    wind = windDetails.speed.toKmPerHourSpeedStr()
)