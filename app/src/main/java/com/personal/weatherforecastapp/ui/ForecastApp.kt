package com.personal.weatherforecastapp.ui

import android.app.Application
import com.personal.weatherforecastapp.di.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ForecastApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ForecastApp)
        }
        MainModule.initialize()
    }
}