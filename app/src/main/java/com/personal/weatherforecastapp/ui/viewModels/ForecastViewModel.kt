package com.personal.weatherforecastapp.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.personal.weatherforecastapp.bases.BaseViewModel
import com.personal.weatherforecastapp.domain.model.WeatherModel
import com.personal.weatherforecastapp.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ForecastViewModel(private val repository: ForecastRepository) : BaseViewModel() {

    private val _weatherSummary = MutableLiveData<WeatherModel>()
    val weatherSummary: LiveData<WeatherModel> = _weatherSummary

    private val _weathers = MutableLiveData<List<WeatherModel>>()
    val weathers: LiveData<List<WeatherModel>> = _weathers

    fun getForecasts(queryStr: String) {
        viewModelScope.launch {
            repository.getForecasts(queryStr)
                .onStart { startLoading() }
                .onCompletion { stopLoading() }
                .catch {
                    setMessage(it.message.orEmpty())
                    stopLoading()
                }
                .collect {
                    val summary = it[0]
                    val list = it.subList(1, it.size)
                    _weatherSummary.postValue(summary)
                    _weathers.postValue(list)
                }
        }
    }

}