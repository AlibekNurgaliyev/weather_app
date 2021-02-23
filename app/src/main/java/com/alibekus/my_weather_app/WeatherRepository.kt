package com.alibekus.my_weather_app

import androidx.annotation.WorkerThread
import com.alibekus.my_weather_app.API.Weather
import kotlinx.coroutines.flow.Flow

class WeatherRepository(private val weatherDao: WeatherDao) {
    val storedWeather: Flow<List<WeatherDB>> = weatherDao.getStoredWeather()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(weatherDB: WeatherDB) {
        weatherDao.insert(weatherDB)
    }
}