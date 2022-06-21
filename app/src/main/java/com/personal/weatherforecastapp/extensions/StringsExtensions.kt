package com.personal.weatherforecastapp.extensions

import java.text.SimpleDateFormat
import java.util.*

val String.Companion.EMPTY: String get() = ""

fun String?.takeIfNotBlank() = takeIf { it?.isNotBlank().orFalse() }

fun String?.getImageSrc() = "https://openweathermap.org/img/wn/$this.png"

fun String?.toFormattedStrDate(): String {
    val locale: Locale = Locale.getDefault()
    val formatter = SimpleDateFormat("yyyy-M-dd hh:mm:ss", locale)
    return this?.let { str -> formatter.parse(str) }?.let { date ->
        SimpleDateFormat("E, MMM d yyyy hh:mm:ss", locale).format(date)
    } ?: String.EMPTY
}

fun String?.concatTo(value: String?) = "${this.orEmpty()}, ${value.orEmpty()}"