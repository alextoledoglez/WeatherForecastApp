package com.personal.weatherforecastapp.ui.viewHolders

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.personal.weatherforecastapp.databinding.ItemListWeatherBinding
import com.personal.weatherforecastapp.domain.model.WeatherModel
import com.personal.weatherforecastapp.extensions.toLayoutInflater
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class WeatherViewHolder(
    private val binding: ItemListWeatherBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(weather: WeatherModel) {
        with(binding) {
            Picasso.get().load(weather.imageSrc)
                .into(ivWeather, object : Callback {
                    override fun onSuccess() {
                        pbLoader.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        pbLoader.visibility = View.GONE
                    }
                })
            tvDate.text = weather.date
            tvTemperature.text = weather.temperature
        }
    }

    companion object {
        fun newInstance(parent: ViewGroup) = WeatherViewHolder(
            ItemListWeatherBinding.inflate(parent.context.toLayoutInflater(), parent, false)
        )
    }
}