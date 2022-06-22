package com.personal.weatherforecastapp.data.mappers

import com.personal.weatherforecastapp.data.entities.ForecastEntity

fun ForecastEntity.toWeathersModel() = this.list.map { it.toModel(this.cityDetailsEntity) }