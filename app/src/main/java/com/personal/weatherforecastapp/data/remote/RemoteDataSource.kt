package com.personal.weatherforecastapp.data.remote

import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val apiService: ApiService) {
    fun getForecasts(queryStr: String?) = flow { emit(apiService.getForecasts(queryStr.orEmpty())) }
}