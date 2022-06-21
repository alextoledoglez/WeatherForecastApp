package com.personal.weatherforecastapp.ui

import com.personal.weatherforecastapp.bases.BaseActivity
import com.personal.weatherforecastapp.databinding.ForecastActivityBinding
import com.personal.weatherforecastapp.extensions.viewBinding

class ForecastActivity : BaseActivity<Nothing>() {
    override val binding by viewBinding(ForecastActivityBinding::inflate)
}
