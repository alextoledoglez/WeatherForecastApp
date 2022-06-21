package com.personal.weatherforecastapp.extensions

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.location.Geocoder
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.personal.weatherforecastapp.R
import java.util.*

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) { bindingInflater.invoke(layoutInflater) }

internal fun <V : Any> AppCompatActivity.getViewModelClass() = toViewModelClass<V>().kotlin

private fun Activity.checkPermissionsFrom(permissions: Array<String>, permissionId: Int): Boolean {
    val permissionsList = permissions.toList()
    val havePermissions = permissionsList.all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }
    if (!havePermissions) {
        if (permissionsList.any { ActivityCompat.shouldShowRequestPermissionRationale(this, it) }) {
            AlertDialog.Builder(this)
                .setTitle(R.string.location_permission_title)
                .setMessage(R.string.location_permission_message)
                .setPositiveButton(R.string.ok) { _, _ ->
                    ActivityCompat.requestPermissions(this, permissions, permissionId)
                }
                .setNegativeButton(R.string.cancel) { _, _ -> }
                .create()
                .show()
        } else
            ActivityCompat.requestPermissions(this, permissions, permissionId)
        return false
    }
    return true
}

@SuppressLint("MissingPermission")
fun Activity.setLocationListenerFor(
    locationClient: FusedLocationProviderClient?,
    onLocationChange: (location: String, canSummit: Boolean) -> Unit
) {
    val context = this
    val hasLocationPermissions = this.checkPermissionsFrom(
        Manifest::class.LOCATION_PERMISSIONS, Manifest::class.LOCATION_PERMISSION_ID
    )
    if (hasLocationPermissions) {
        locationClient?.lastLocation?.addOnSuccessListener { location ->
            try {
                val maxResults = 1
                val addresses = Geocoder(context, Locale.getDefault()).getFromLocation(
                    location.latitude, location.longitude, maxResults
                )
                val address = addresses[0]
                val locationStr = address.subAdminArea.concatTo(address.countryCode)
                onLocationChange(locationStr, locationStr.isNotEmpty())
            } catch (e: Exception) {
                println(e.stackTrace)
            }
        }
    }
}