package me.dimas.mvpweatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.forecast_list_item.view.*
import me.dimas.mvpweatherapp.R
import me.dimas.mvpweatherapp.ui.ForecastItemViewModel
import java.text.SimpleDateFormat
import java.util.*

class ForecastAdapter(): RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    var forecastList = mutableListOf<ForecastItemViewModel>()

    fun addForecast(list: List<ForecastItemViewModel>) {
        forecastList.clear()
        forecastList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.forecast_list_item, parent, false)
        return ForecastViewHolder(view)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        forecastList[position].let {
            holder.bind(forecastElement = it)
        }
    }

    class ForecastViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(forecastElement: ForecastItemViewModel) {
            itemView.degree_text.text = "${forecastElement.degreeDay} °C ${forecastElement.description}"
            itemView.date_text.text = getDate(forecastElement.date)

            Glide.with(itemView.context)
                    .load("http://openweathermap.org/img/w/${forecastElement.icon}.png")
                    .into(itemView.weather_icon)
        }

        private fun getDate(date: Long): String {
            val timerFormatter = SimpleDateFormat("dd.MM.yyyy")
            return timerFormatter.format(Date(date*1000L))
        }
    }
}
