package com.personal.weatherforecastapp.domain.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherModel(
    val id: Int,
    val imageSrc: String,
    val temperature: String,
    val location: String,
    val date: String,
    val description: String,
    val humidity: String,
    val wind: String
) : Parcelable {

    companion object {

        val DIFF_UTIL_ITEM_CALLBACK = object : DiffUtil.ItemCallback<WeatherModel>() {
            override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel) =
                oldItem == newItem
        }
    }

}