package com.alibekus.my_weather_app

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    val storedWeather: LiveData<List<WeatherDB>> = repository.storedWeather.asLiveData()

    fun insert(weatherDB: WeatherDB) = viewModelScope.launch {
        repository.insert(weatherDB)
    }
}

class WeatherViewModelFactory(private val repository: WeatherRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}