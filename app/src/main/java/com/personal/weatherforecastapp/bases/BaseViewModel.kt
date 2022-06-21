package com.personal.weatherforecastapp.bases

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.personal.weatherforecastapp.extensions.EMPTY
import com.personal.weatherforecastapp.extensions.FALSE
import com.personal.weatherforecastapp.extensions.NULL

abstract class BaseViewModel : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(Boolean.FALSE)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _message: MutableLiveData<String?> = MutableLiveData(String.EMPTY)
    val message: LiveData<String?> get() = _message

    private val _messageResource: MutableLiveData<Int?> = MutableLiveData(Int.NULL)
    val messageResource: LiveData<Int?> get() = _messageResource

    private fun setLoading(isLoading: Boolean = true) {
        _isLoading.postValue(isLoading)
    }

    fun startLoading() {
        setLoading(isLoading = true)
    }

    fun stopLoading() {
        setLoading(isLoading = false)
    }

    fun setMessage(message: String) {
        _message.postValue(message)
        stopLoading()
    }

    fun setMessageResource(@StringRes messageResource: Int) {
        _messageResource.postValue(messageResource)
        stopLoading()
    }
}