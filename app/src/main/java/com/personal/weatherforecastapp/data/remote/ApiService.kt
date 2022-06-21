package com.personal.weatherforecastapp.data.remote

import com.personal.weatherforecastapp.data.entities.ForecastEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast")
    suspend fun getForecasts(
        @Query("q") query: String = "",
        @Query("APPID") appId: String = "aaa4ef9ba9974f09efc03fe4a3d8739a"
    ): ForecastEntity
}