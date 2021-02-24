package com.alibekus.my_weather_app.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/weather?")
    fun getCurrentWeatherData(
        @Query("id") city_id: String,
        @Query("APPID") app_id: String

    ): Call<WeatherResponse>
}
