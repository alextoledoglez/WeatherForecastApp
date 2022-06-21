package com.personal.weatherforecastapp.extensions

val Int.Companion.ZERO: Int get() = 0

val Int.Companion.NULL: Int? get() = null

val Int.Companion.DEFAULT_DECIMAL_PLACES: Int get() = 2

fun Int?.orZero() = this ?: Int.ZERO

fun Int?.toFormattedHumidity() = "${this.orZero()}%"