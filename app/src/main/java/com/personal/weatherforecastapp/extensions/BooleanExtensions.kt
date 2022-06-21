package com.personal.weatherforecastapp.extensions

val Boolean.Companion.FALSE: Boolean get() = false

fun Boolean?.orFalse() = this ?: Boolean.FALSE