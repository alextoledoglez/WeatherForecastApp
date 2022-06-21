package com.personal.weatherforecastapp.data.repository

import com.personal.weatherforecastapp.data.mappers.toWeathersModel
import com.personal.weatherforecastapp.data.remote.RemoteDataSource
import com.personal.weatherforecastapp.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.map

class ForecastDataRepository(private val dataSource: RemoteDataSource) : ForecastRepository {
    override fun getForecasts(queryStr: String?) = dataSource.getForecasts(queryStr).map {
        it.toWeathersModel()
    }
}