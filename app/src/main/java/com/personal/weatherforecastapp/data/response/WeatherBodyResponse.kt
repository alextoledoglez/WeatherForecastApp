package com.personal.weatherforecastapp.data.response

import com.google.gson.annotations.SerializedName
import com.personal.weatherforecastapp.data.model.Weather
import com.personal.weatherforecastapp.data.utils.Formatter
import com.personal.weatherforecastapp.data.response.details.*

data class WeatherBodyResponse(
    @SerializedName(value = "coord")
    val location: LocationDetails,

    @SerializedName(value = "weather")
    val weatherDetails: List<WeatherDetails>,

    @SerializedName(value = "main")
    val mainDetails: MainDetails,

    @SerializedName(value = "wind")
    val windDetails: WinDetails,

    @SerializedName(value = "clouds")
    val cloudsDetails: CloudsDetails,

    @SerializedName(value = "rain")
    val rainDetails: VolumeDetails,

    @SerializedName(value = "snow")
    val snowDetails: VolumeDetails,

    @SerializedName(value = "dt")
    val dt: Long,

    @SerializedName(value = "sys")
    val sysDetails: SysDetails,

    @SerializedName(value = "timezone")
    val timeZone: Long,

    @SerializedName(value = "name")
    val cityName: String,

    @SerializedName(value = "dt_txt")
    val dtTxt: String
) {
    fun getWeatherModel(cityDetails: CityDetails) = Weather(
        id = weatherDetails[0].id,
        imageSrc = Formatter.getIconSrcFrom(weatherDetails[0].iconCode),
        temperature = Formatter.toCelsiusDegreesTemperatureStr(mainDetails.temp),
        location = Formatter.concatNames(cityDetails.name, cityDetails.country),
        date = Formatter.toFormattedCurrentDate(dtTxt),
        description = weatherDetails[0].description,
        humidity = Formatter.toFormattedHumidity(mainDetails.humidity),
        win = Formatter.toKmPerHourSpeedStr(windDetails.speed)
    )
}


