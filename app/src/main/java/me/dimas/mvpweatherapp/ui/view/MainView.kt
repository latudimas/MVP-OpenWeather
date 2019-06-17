package me.dimas.mvpweatherapp.ui.view

import me.dimas.mvpweatherapp.ErrorTypes
import me.dimas.mvpweatherapp.ui.ForecastItemViewModel

interface MainView {
    fun showSpinner()
    fun hideSpinner()
    fun updateForecast(forecast: List<ForecastItemViewModel>)
    fun showErrorToast(error: ErrorTypes)
}