package me.dimas.mvpweatherapp.ui.view

import me.dimas.mvpweatherapp.ui.ForecastItemViewModel

interface DetailView {
    fun showInformation(forecast: ForecastItemViewModel)
}