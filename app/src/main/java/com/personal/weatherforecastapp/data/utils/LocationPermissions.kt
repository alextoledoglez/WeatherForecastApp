package com.personal.weatherforecastapp.data.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.personal.weatherforecastapp.R
import java.util.*

object LocationPermissions {

    private const val locationPermissionId = 10
    private val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    private val queryStrLiveData = MutableLiveData<String>()
    private var fusedLocationClient: FusedLocationProviderClient? = null

    fun createInstanceFrom(activity: Activity) {
        fusedLocationClient = LocationServices
            .getFusedLocationProviderClient(activity)
    }

    private fun checkFrom(activity: Activity): Boolean {
        val havePermissions = permissions.toList().all {
            ContextCompat.checkSelfPermission(activity, it) ==
                    PackageManager.PERMISSION_GRANTED
        }
        if (!havePermissions) {
            if (permissions.toList().any {
                    ActivityCompat.shouldShowRequestPermissionRationale(activity, it)
                }) {
                AlertDialog.Builder(activity)
                    .setTitle(R.string.location_permission_title)
                    .setMessage(R.string.location_permission_message)
                    .setPositiveButton(R.string.ok) { _, _ ->
                        ActivityCompat.requestPermissions(
                            activity,
                            permissions,
                            locationPermissionId
                        )
                    }
                    .setNegativeButton(R.string.cancel) { _, _ -> }
                    .create()
                    .show()
            } else {
                ActivityCompat.requestPermissions(activity, permissions, locationPermissionId)
            }
            return false
        }
        return true
    }

    @SuppressLint("MissingPermission")
    fun setLocationQueryStringListenerFor(activity: Activity) {
        if (checkFrom(activity)) {
            fusedLocationClient?.lastLocation?.addOnSuccessListener { location: Location? ->
                location?.let {
                    location.apply {
                        try {
                            val addresses: List<Address> = Geocoder(activity, Locale.getDefault())
                                .getFromLocation(latitude, longitude, 1)
                            val address = addresses[0]
                            val cityName = address.subAdminArea
                            val countryCode = address.countryCode
                            queryStrLiveData.value = Formatter.concatNames(cityName, countryCode)
                        } catch (e: Exception) {
                            println(e.stackTrace)
                        }
                    }
                }
            }
        }
    }

    fun getQueryStrLiveData(): MutableLiveData<String> {
        return queryStrLiveData
    }

    fun getLocationPermissionId(): Int {
        return locationPermissionId
    }

    fun isNotGranted(grantResults: IntArray): Boolean {
        return grantResults.isEmpty() ||
                grantResults[0] != PackageManager.PERMISSION_GRANTED
    }
}