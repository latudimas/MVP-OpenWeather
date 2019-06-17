package me.dimas.mvpweatherapp.dagger.component

import dagger.Component
import me.dimas.mvpweatherapp.dagger.module.OpenWeatherAPIModule
import me.dimas.mvpweatherapp.ui.presenter.MainPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(OpenWeatherAPIModule::class))
interface OpenWeatherAPIComponent {
    fun inject(presenter: MainPresenter);

}