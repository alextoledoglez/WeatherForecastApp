package com.personal.weatherforecastapp.data.utils

import android.content.Context
import okhttp3.Cache
import java.io.File

object CacheManager {

    var MAX_AGE_VALUE = 5
    var HEADER_PRAGMA = "Pragma"
    var HEADER_CACHE_CONTROL = "Cache-Control"
    private const val CACHE_CHILD = "okHttpCache"
    private const val CACHE_SIZE = (5 * 1024 * 1024).toLong()

    private lateinit var instance: Cache

    fun initializeFrom(context: Context) {
        instance = Cache(File(context.cacheDir, CACHE_CHILD), CACHE_SIZE)
    }

    fun getCacheInstance(): Cache {
        return instance
    }
}