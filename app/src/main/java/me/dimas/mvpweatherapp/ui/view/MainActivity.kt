package me.dimas.mvpweatherapp.ui.view

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import me.dimas.mvpweatherapp.ErrorTypes
import me.dimas.mvpweatherapp.R
import me.dimas.mvpweatherapp.dagger.component.DaggerOpenWeatherAPIComponent
import me.dimas.mvpweatherapp.dagger.component.OpenWeatherAPIComponent
import me.dimas.mvpweatherapp.dagger.module.OpenWeatherAPIModule
import me.dimas.mvpweatherapp.ui.ForecastItemViewModel
import me.dimas.mvpweatherapp.ui.adapter.ForecastAdapter
import me.dimas.mvpweatherapp.ui.presenter.MainPresenter

class MainActivity: AppCompatActivity(), MainView {

    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDI()
        setContentView(R.layout.activity_main)
        initializeForecastList()
    }

    private fun injectDI() {
        DaggerOpenWeatherAPIComponent
                .builder()
                .openWeatherAPIModule(OpenWeatherAPIModule())
                .build()
                .inject(presenter)

    }

    private fun initializeForecastList() {
        forecast_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ForecastAdapter()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_page_menu, menu)

        val menuItem = menu?.findItem(R.id.search_button)
        val searchMenuItem = menuItem?.actionView

        if (searchMenuItem is SearchView) {
            searchMenuItem.queryHint = getString(R.string.menu_search_hint)
            searchMenuItem.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    getForecast(query)
                    menuItem.collapseActionView()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
        return true
    }

    private fun getForecast(query: String) = presenter.getForecastForSevenDays(query)

    inline fun <reified T> Any.safeCast() = this as? T

    fun Activity.toast(toastMessage: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, toastMessage, duration).show()
    }

    override fun showSpinner() {
        forecast_list.visibility = View.GONE
        empty_state_text.visibility = View.GONE
        loading_spinner.visibility = View.VISIBLE
    }

    override fun hideSpinner() {
        forecast_list.visibility = View.VISIBLE
        loading_spinner.visibility = View.GONE
    }

    override fun updateForecast(forecast: List<ForecastItemViewModel>) {
        if (forecast.isEmpty()) empty_state_text.visibility = View.VISIBLE
        forecast_list.adapter?.safeCast<ForecastAdapter>()?.addForecast(forecast)
    }

    override fun showErrorToast(error: ErrorTypes) {
        when (error) {
            ErrorTypes.CALL_ERROR -> toast(getString(R.string.connection_error_message))
            ErrorTypes.NO_RESULT_FOUND -> toast(getString(R.string.city_not_found_toast_message))
            ErrorTypes.MISSING_API_KEY -> toast(getString(R.string.missing_api_key_message))
        }

        loading_spinner.visibility = View.GONE
        empty_state_text.visibility = View.VISIBLE
    }
}