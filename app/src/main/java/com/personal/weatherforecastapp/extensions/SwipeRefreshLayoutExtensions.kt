package com.personal.weatherforecastapp.extensions

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.personal.weatherforecastapp.R

fun SwipeRefreshLayout.updateRefreshing(isRefreshing: Boolean) {
    if (this.isRefreshing != isRefreshing)
        this.isRefreshing = isRefreshing
}

fun SwipeRefreshLayout.stopRefreshing() {
    this.isRefreshing = false
}

fun SwipeRefreshLayout.setTheme() {
    setColorSchemeResources(R.color.colorPrimaryDark)
    setProgressBackgroundColorSchemeResource(android.R.color.transparent)
}