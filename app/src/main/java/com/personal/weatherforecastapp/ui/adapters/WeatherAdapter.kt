package com.personal.weatherforecastapp.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.personal.weatherforecastapp.domain.model.WeatherModel
import com.personal.weatherforecastapp.domain.model.WeatherModel.Companion.DIFF_UTIL_ITEM_CALLBACK
import com.personal.weatherforecastapp.ui.viewHolders.WeatherViewHolder

class WeatherAdapter : ListAdapter<WeatherModel, WeatherViewHolder>(DIFF_UTIL_ITEM_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = WeatherViewHolder.newInstance(parent)

    override fun onBindViewHolder(viewHolder: WeatherViewHolder, position: Int) {
        viewHolder.bindView(currentList[position])
    }
}