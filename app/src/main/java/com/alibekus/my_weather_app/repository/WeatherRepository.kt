package com.alibekus.my_weather_app.repository

import androidx.annotation.WorkerThread
import com.alibekus.my_weather_app.database.Weather
import com.alibekus.my_weather_app.database.WeatherDao
import kotlinx.coroutines.flow.Flow

class WeatherRepository(private val weatherDao: WeatherDao) {
    val storedWeather: Flow<List<Weather>> = weatherDao.getStoredWeather()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(weather: Weather) {
        weatherDao.insert(weather)
    }
}