package me.dimas.mvpweatherapp.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.dimas.mvpweatherapp.ErrorTypes
import me.dimas.mvpweatherapp.R
import me.dimas.mvpweatherapp.ui.ForecastItemViewModel
import me.dimas.mvpweatherapp.ui.presenter.MainPresenter

class MainActivity: AppCompatActivity(), MainView {

    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showSpinner() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideSpinner() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateForecast(forecast: List<ForecastItemViewModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorToast(error: ErrorTypes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}