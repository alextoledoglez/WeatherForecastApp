package com.personal.weatherforecastapp.data.utils

import android.content.Context
import android.widget.Toast

object ToastManager {
    private fun showText(context: Context, messageResource: Int, duration: Int) {
        Toast.makeText(
            context,
            context.getString(messageResource),
            duration
        ).show()
    }

    private fun showText(context: Context, message: String, duration: Int) {
        Toast.makeText(
            context,
            message,
            duration
        ).show()
    }

    fun showShortText(context: Context, messageResource: Int) {
        showText(context, messageResource, Toast.LENGTH_SHORT)
    }

    fun showShortText(context: Context, message: String) {
        showText(context, message, Toast.LENGTH_SHORT)
    }

    fun showLongText(context: Context, messageResource: Int) {
        showText(context, messageResource, Toast.LENGTH_LONG)
    }

    fun showLongText(context: Context, message: String) {
        showText(context, message, Toast.LENGTH_LONG)
    }
}