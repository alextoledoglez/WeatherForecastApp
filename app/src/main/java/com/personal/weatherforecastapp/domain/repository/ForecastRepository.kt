package com.personal.weatherforecastapp.domain.repository

import com.personal.weatherforecastapp.domain.model.WeatherModel
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {
    fun getForecasts(queryStr: String?): Flow<List<WeatherModel>>
}