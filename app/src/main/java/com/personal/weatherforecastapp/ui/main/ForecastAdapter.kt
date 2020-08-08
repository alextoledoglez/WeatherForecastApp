package com.personal.weatherforecastapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.personal.weatherforecastapp.R
import com.personal.weatherforecastapp.data.model.Weather
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_item.view.*

class ForecastAdapter(private val forecasts: List<Weather>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ForecastViewHolder(view)
    }

    override fun getItemCount() = forecasts.count()

    override fun onBindViewHolder(viewHolder: ForecastViewHolder, position: Int) {
        viewHolder.bindView(forecasts[position])
    }

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val forecastDate: TextView = itemView.forecastDateTextView
        private val forecastImage: ImageView = itemView.forecastImageView
        private val forecastTemperature: TextView = itemView.forecastTemperatureTextView

        fun bindView(forecast: Weather) {
            forecastDate.text = forecast.date
            Picasso.with(itemView.context)
                .load(forecast.imageSrc)
                .into(forecastImage)
            forecastTemperature.text = forecast.temperature
        }
    }
}