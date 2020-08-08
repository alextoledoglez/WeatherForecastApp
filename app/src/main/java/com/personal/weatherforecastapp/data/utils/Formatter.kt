package com.personal.weatherforecastapp.data.utils

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

object Formatter {

    private const val SPEED_BASE_VALUE: Double = 3.6
    private const val DEFAULT_DECIMAL_PLACES: Int = 2
    private const val TEMPERATURE_BASE_VALUE: Double = 273.15

    private fun roundTo(value: Double, decimalPlaces: Int = DEFAULT_DECIMAL_PLACES): BigDecimal {
        return BigDecimal(value).setScale(
            decimalPlaces,
            RoundingMode.HALF_UP
        )
    }

    fun getIconSrcFrom(iconCode: String): String {
        return "https://openweathermap.org/img/wn/$iconCode.png"
    }

    fun toFormattedCurrentDate(dateStr: String): String {
        val locale: Locale = Locale.getDefault()
        val formatter = SimpleDateFormat("yyyy-M-dd hh:mm:ss", locale)
        val date = formatter.parse(dateStr)
        date?.let {
            return SimpleDateFormat("E, MMM d yyyy  hh:mm:ss", locale)
                .format(date)
        }
        return ""
    }

    private fun toCelsiusDegreesTemperature(value: Double): BigDecimal {
        return roundTo(
            value - TEMPERATURE_BASE_VALUE,
            0
        )
    }

    fun toCelsiusDegreesTemperatureStr(value: Double): String {
        return "${toCelsiusDegreesTemperature(
            value
        )}°"
    }

    fun concatNames(cityName: String, countryName: String): String {
        return "$cityName, $countryName"
    }

    fun toFormattedHumidity(value: Int): String {
        return "$value%"
    }

    private fun toKmPerHourSpeed(value: Double): BigDecimal {
        return roundTo(
            value * SPEED_BASE_VALUE,
            0
        )
    }

    fun toKmPerHourSpeedStr(value: Double): String {
        return "${toKmPerHourSpeed(
            value
        )} km/h"
    }
}