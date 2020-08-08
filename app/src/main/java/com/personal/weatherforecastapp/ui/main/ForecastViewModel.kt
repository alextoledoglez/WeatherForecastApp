package com.personal.weatherforecastapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.personal.weatherforecastapp.R
import com.personal.weatherforecastapp.data.model.Weather
import com.personal.weatherforecastapp.data.response.ForecastBodyResponse
import com.personal.weatherforecastapp.data.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastViewModel() : ViewModel() {

    val weatherLiveData = MutableLiveData<List<Weather>>()
    val responseLiveData = MutableLiveData<String>()

    fun getForecasts(queryStr: String) {
        RetrofitService
            .apiService
            .getForecasts(queryStr)
            .enqueue(object : Callback<ForecastBodyResponse> {
                override fun onResponse(
                    call: Call<ForecastBodyResponse>,
                    response: Response<ForecastBodyResponse>
                ) {
                    if (response.isSuccessful) {
                        val weathers: MutableList<Weather> = mutableListOf()
                        response.body()?.let { forecastBodyResponse ->
                            for (weatherBodyResponse in forecastBodyResponse.list) {
                                val weather =
                                    weatherBodyResponse.getWeatherModel(forecastBodyResponse.cityDetails)
                                weathers.add(weather)
                            }
                        }
                        weatherLiveData.value = weathers
                    }
                    responseLiveData.value = response.message()
                    if (response.raw().cacheResponse() != null) {
                        println(R.string.response_from_cache_message)
                    } else if (response.raw().networkResponse() != null) {
                        println(R.string.response_from_network_message)
                    }
                }

                override fun onFailure(call: Call<ForecastBodyResponse>, t: Throwable) {
                    println(t.stackTrace)
                }
            })
    }

    class MainViewModelFactory() :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ForecastViewModel() as T
        }
    }
}