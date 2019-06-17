package me.dimas.mvpweatherapp.ui.presenter

import android.content.Intent
import me.dimas.mvpweatherapp.ui.ForecastItemViewModel
import me.dimas.mvpweatherapp.ui.view.DetailView

class DetailPresenter(val view: DetailView) {
    fun values(intent: Intent?) {
        intent?.extras?.let {
            var item: ForecastItemViewModel = it["extra"] as ForecastItemViewModel
            view.showInformation(item)
        }
    }
}