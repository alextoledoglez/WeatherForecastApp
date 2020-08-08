package com.personal.weatherforecastapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.personal.weatherforecastapp.data.service.RetrofitService
import com.personal.weatherforecastapp.data.model.Weather
import com.personal.weatherforecastapp.data.response.ForecastBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastViewModel() : ViewModel() {

    val weatherLiveData = MutableLiveData<List<Weather>>()
    val responseLiveData = MutableLiveData<String>()

    fun getWeathers(queryStr: String) {
        RetrofitService
            .apiService
            .getWeathers(queryStr)
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