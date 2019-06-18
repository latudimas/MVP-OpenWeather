package me.dimas.mvpweatherapp.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.details_view.*
import me.dimas.mvpweatherapp.R
import me.dimas.mvpweatherapp.ui.ForecastItemViewModel
import me.dimas.mvpweatherapp.ui.presenter.DetailPresenter
import java.text.SimpleDateFormat

class DetailActivity: AppCompatActivity(), DetailView {

    val presenter = DetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_view)
        presenter.values(intent)
    }

    override fun showInformation(forecast: ForecastItemViewModel) {
//        detail_city_name.text = forecast.city
    }

    private fun getDate(date: Long): String {
        val timeFormatter = SimpleDateFormat("dd.MM.yyyy")
        return timeFormatter.format(date*1000L)
    }
}