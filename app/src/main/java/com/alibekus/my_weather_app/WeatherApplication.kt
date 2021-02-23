package com.alibekus.my_weather_app

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WeatherApplication : Application() {

    val applicationScope = CoroutineScope (SupervisorJob())

    val database by lazy { WeatherRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WeatherRepository(database.weatherdDao()) }
}