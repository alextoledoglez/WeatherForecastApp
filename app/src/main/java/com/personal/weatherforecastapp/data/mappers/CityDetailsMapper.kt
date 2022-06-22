package com.personal.weatherforecastapp.data.mappers

import com.personal.weatherforecastapp.data.entities.CityDetailsEntity
import com.personal.weatherforecastapp.extensions.concatTo

fun CityDetailsEntity.toStrLocation() = this.name.concatTo(this.country)