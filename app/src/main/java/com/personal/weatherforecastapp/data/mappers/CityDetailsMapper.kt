package com.personal.weatherforecastapp.data.mappers

import com.personal.weatherforecastapp.data.entities.CityDetails
import com.personal.weatherforecastapp.extensions.concatTo

fun CityDetails.toStrLocation() = this.name.concatTo(this.country)