package com.personal.weatherforecastapp.data.service

import com.personal.weatherforecastapp.data.response.ForecastBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast")
    fun getForecasts(
        @Query("q") query: String = "",
        @Query("APPID") appId: String = "aaa4ef9ba9974f09efc03fe4a3d8739a"
    ): Call<ForecastBodyResponse>
}