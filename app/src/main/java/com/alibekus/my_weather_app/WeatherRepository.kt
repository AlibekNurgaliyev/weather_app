package com.alibekus.my_weather_app

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow


class WeatherRepository(private val weatherDao: WeatherDao) {
    val storedWeather: Flow<List<Weather>> = weatherDao.getStoredWeather()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(weather: Weather) {
        weatherDao.insert(weather)
    }
}