package com.personal.weatherforecastapp.ui.fragments

import android.Manifest
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.personal.weatherforecastapp.R
import com.personal.weatherforecastapp.bases.BaseFragment
import com.personal.weatherforecastapp.databinding.ForecastFragmentBinding
import com.personal.weatherforecastapp.domain.model.WeatherModel
import com.personal.weatherforecastapp.extensions.*
import com.personal.weatherforecastapp.ui.adapters.WeatherAdapter
import com.personal.weatherforecastapp.ui.viewModels.ForecastViewModel
import com.squareup.picasso.Picasso

class ForecastFragment : BaseFragment<ForecastViewModel>() {

    override val binding by viewBinding(ForecastFragmentBinding::inflate)
    private val listAdapter: WeatherAdapter by lazy { WeatherAdapter() }
    private val locationClient: FusedLocationProviderClient? by lazy {
        LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onStart() {
        super.onStart()
        activity?.setLocationListenerFor(locationClient, binding.svLocation::setQuery)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Manifest::class.LOCATION_PERMISSION_ID -> if (isNotGranted(grantResults))
                context?.showToastShortText(R.string.permission_denied_by_user_message)
            else
                activity?.setLocationListenerFor(
                    locationClient, binding.svLocation::setQuery
                )
        }
    }

    override fun initComponents() {
        with(binding) {
            svLocation.apply {
                isIconified = false
                queryHint = getString(R.string.search_query_hint)
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        ibSearch.callOnClick()
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        cvHeader.visibility = View.GONE
                        pbForecast.visibility = View.GONE
                        return false
                    }
                })
            }

            ibSearch.setOnClickListener {
                val queryStr: String = svLocation.query.toString()
                if (queryStr.isEmpty()) {
                    context?.showToastShortText(R.string.enter_valid_place_message)
                } else {
                    pbForecast.visibility = View.VISIBLE
                    viewModel.getForecasts(queryStr)
                }
            }

            rvWeathers.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = listAdapter
            }
        }
    }

    override fun initObservers() {
        with(viewModel) {
            isLoading.observe(viewLifecycleOwner) {
                binding.pbForecast.isVisible = it.orFalse()
            }
            message.observe(viewLifecycleOwner) { message ->
                binding.cvHeader.isVisible = message.orEmpty().isNotEmpty()
                message?.takeIfNotBlank()?.let { context?.showToastShortText(it) }
            }
            weatherSummary.observe(viewLifecycleOwner) { updateHeader(it) }
            weathers.observe(viewLifecycleOwner) { listAdapter.submitList(it) }
        }
    }

    private fun updateHeader(weather: WeatherModel) {
        with(binding) {
            cvHeader.visibility = View.VISIBLE
            Picasso.get()
                .load(weather.imageSrc)
                .into(binding.ivWeather)
            tvLocation.text = weather.location
            tvDate.text = weather.date
            tvDescription.text = weather.description
            tvTemperature.text = weather.temperature
            tvHumidityValue.text = weather.humidity
            tvWindValue.text = weather.wind
        }
    }
}