package com.personal.weatherforecastapp.extensions

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.abs

val Double.Companion.ZERO: Double get() = 0.0

val Double.Companion.NULL: Double? get() = null

val Double.Companion.SPEED_BASE_VALUE: Double get() = 3.6

val Double.Companion.TEMPERATURE_BASE_VALUE: Double get() = 273.15

fun Double?.orZero() = this ?: Double.ZERO

private fun Double?.roundTo(
    decimalPlaces: Int = Int.DEFAULT_DECIMAL_PLACES
): BigDecimal = BigDecimal(this.orZero()).setScale(decimalPlaces, RoundingMode.HALF_UP)

private fun Double?.toCelsiusDegreesTemperature() =
    abs(x = this.orZero() - Double.TEMPERATURE_BASE_VALUE).roundTo(decimalPlaces = Int.ZERO)

fun Double?.toCelsiusDegreesTemperatureStr() = "${this.toCelsiusDegreesTemperature()}°"

fun Double?.toKmPerHourSpeed() = (this.orZero() * Double.SPEED_BASE_VALUE).roundTo(Int.ZERO)

fun Double?.toKmPerHourSpeedStr() = "${this.toKmPerHourSpeed()} km/h"