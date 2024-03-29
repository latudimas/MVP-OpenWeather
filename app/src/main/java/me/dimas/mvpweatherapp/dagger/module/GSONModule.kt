package me.dimas.mvpweatherapp.dagger.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GSONModule {
    @Provides @Singleton
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }
}

