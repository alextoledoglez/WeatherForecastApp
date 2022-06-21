package com.personal.weatherforecastapp.extensions

import android.Manifest
import android.content.pm.PackageManager
import kotlin.reflect.KClass

val KClass<Manifest>.LOCATION_PERMISSION_ID: Int get() = 10

val KClass<Manifest>.LOCATION_PERMISSIONS: Array<String>
    get() = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

fun isNotGranted(grantResults: IntArray): Boolean = grantResults.isEmpty() ||
        grantResults[0] != PackageManager.PERMISSION_GRANTED