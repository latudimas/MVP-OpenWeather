package me.dimas.mvpweatherapp.ui.presenter

import me.dimas.mvpweatherapp.BuildConfig
import me.dimas.mvpweatherapp.ErrorTypes
import me.dimas.mvpweatherapp.api.OpenWeatherAPI
import me.dimas.mvpweatherapp.data.ForecastDetail
import me.dimas.mvpweatherapp.data.WeatherResponse
import me.dimas.mvpweatherapp.ui.ForecastItemViewModel
import me.dimas.mvpweatherapp.ui.view.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainPresenter(val view: MainView) {
    @Inject lateinit var api: OpenWeatherAPI

    fun getForecastForSevenDays(cityName: String) {
        if (BuildConfig.OPEN_WEATHER_API_KEY == "f43578d8b01f018f5c93b84c316991fe") {
            view.showErrorToast(ErrorTypes.MISSING_API_KEY)
         }

        view.showSpinner()
        api.dailyForecast(cityName, 7).enqueue(object: Callback<WeatherResponse> {

            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                response.body()?.let {
                    createListForView(it)
                    view.hideSpinner()
                } ?: view.showErrorToast(ErrorTypes.NO_RESULT_FOUND)
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                view.showErrorToast(ErrorTypes.CALL_ERROR)
                t.printStackTrace()
            }
        })
    }

    private fun createListForView(weatherResponse: WeatherResponse) {
        val forecast = mutableListOf<ForecastItemViewModel>()
        for (forecastDetail: ForecastDetail in weatherResponse.forecast) {
            val dayTemp = forecastDetail.temperature.dayTemperature
            val forecastItem = ForecastItemViewModel(degreeDay = dayTemp.toString(),
                date = forecastDetail.date,
                icon = forecastDetail.description[0].icon,
                description = forecastDetail.description[0].description)
            forecast.add(forecastItem)
        }
        view.updateForecast(forecast)
    }
}