package com.alibekus.my_weather_app.database

import android.app.Application
import com.alibekus.my_weather_app.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WeatherApplication : Application() {

    val applicationScope = CoroutineScope (SupervisorJob())
    val database by lazy { WeatherRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WeatherRepository(database.weatherdDao()) }
}