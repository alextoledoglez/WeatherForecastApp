package com.personal.weatherforecastapp.ui.main

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.personal.weatherforecastapp.R
import com.personal.weatherforecastapp.data.utils.CacheManager
import com.personal.weatherforecastapp.data.utils.LocationPermissions
import com.personal.weatherforecastapp.data.utils.ToastManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.forecast_activity.*

class ForecastActivity : AppCompatActivity() {

    private lateinit var viewModel: ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forecast_activity)

        progressBar.visibility = View.GONE
        cardsLinearLayout.visibility = View.GONE

        CacheManager.initializeFrom(this)
        LocationPermissions.createInstanceFrom(this)

        locationSearchView.isIconifiedByDefault = false
        locationSearchView.queryHint = getString(R.string.search_query_hint)
        locationSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                checkImageButton.callOnClick()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                progressBar.visibility = View.GONE
                cardsLinearLayout.visibility = View.GONE
                return false
            }
        })

        viewModel = ViewModelProvider(
            this,
            ForecastViewModel.MainViewModelFactory()
        ).get(ForecastViewModel::class.java)

        LocationPermissions.getQueryStrLiveData()
            .observe(this, Observer { queryStr ->
                locationSearchView.setQuery(queryStr, queryStr.isNotEmpty())
            })

        viewModel.weatherLiveData
            .observe(this, Observer {
                it?.let { weathers ->
                    with(forecastRecyclerView) {
                        layoutManager =
                            LinearLayoutManager(
                                this.context,
                                RecyclerView.VERTICAL,
                                false
                            )
                        val weatherSubList = weathers
                            .subList(1, weathers.size)
                        adapter = ForecastAdapter(weatherSubList)
                    }
                    val weather = weathers[0]
                    Picasso.with(this)
                        .load(weather.imageSrc)
                        .into(weatherImageView)
                    locationTextView.text = weather.location
                    dateTextView.text = weather.date
                    descriptionTextView.text = weather.description
                    temperatureTextView.text = weather.temperature
                    humidityValueTextView.text = weather.humidity
                    winValueTextView.text = weather.win
                }
            })

        viewModel.responseLiveData
            .observe(this, Observer { responseMessage ->
                val message: String
                if (responseMessage.isNotEmpty() && responseMessage == getString(R.string.ok)) {
                    message = getString(R.string.success)
                    cardsLinearLayout.visibility = View.VISIBLE
                } else {
                    message = responseMessage
                    cardsLinearLayout.visibility = View.GONE
                }
                progressBar.visibility = View.GONE
                ToastManager.showShortText(this, message)
            })

        checkImageButton.setOnClickListener {
            val queryStr: String = locationSearchView.query.toString()
            if (queryStr.isEmpty()) {
                ToastManager.showShortText(
                    this,
                    R.string.enter_valid_place_message
                )
            } else {
                progressBar.visibility = View.VISIBLE
                viewModel.getForecasts(queryStr)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        LocationPermissions.setLocationQueryStringListenerFor(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            LocationPermissions.getLocationPermissionId() -> {
                if (LocationPermissions.isNotGranted(grantResults)) {
                    ToastManager.showShortText(this, R.string.permission_denied_by_user_message)
                } else {
                    LocationPermissions.setLocationQueryStringListenerFor(this)
                }
            }
        }
    }
}
