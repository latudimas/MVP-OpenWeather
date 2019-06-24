package me.dimas.mvpweatherapp.dagger.component

import dagger.Component
import me.dimas.mvpweatherapp.dagger.module.OpenWeatherAPIModule
import me.dimas.mvpweatherapp.ui.presenter.MainPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [OpenWeatherAPIModule::class])
interface OpenWeatherAPIComponent {
    fun inject(presenter: MainPresenter);

}