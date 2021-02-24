package com.alibekus.my_weather_app

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.alibekus.my_weather_app.API.WeatherResponse
import com.alibekus.my_weather_app.API.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

private lateinit var getAlmatyId: ImageButton

class CitiesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)
        onBind()
        getCurrentWeatherData()

        getAlmatyId.setOnClickListener {
            replyIntent()
        }
    }

    private fun replyIntent() {
        val replyIntent = Intent()
        replyIntent.putExtra(EXTRA_REPLY, weatherData)
        setResult(Activity.RESULT_OK, replyIntent)
        finish()
    }

    private fun onBind() {
        getAlmatyId = findViewById(R.id.activity_cities_almaty)
    }

    private fun getCurrentWeatherData() {
        val calendar: Calendar = Calendar.getInstance()
        val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val date: String = simpleDateFormat.format(calendar.time)

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(city_id, AppId)
        call.enqueue(object : Callback<WeatherResponse> {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.code() == 200) {

                    val weatherResponse = response.body()!!
                    val stringBuilder =
                        "Country: " +
                                weatherResponse.sys!!.country + "\n" +
                                "City: " +
                                weatherResponse.name + "\n" +
                                "Temperature: %.1f".format(convertToCelsius(weatherResponse.main!!.temp)) + " °C\n" +
                                "Feels like: %.1f".format(convertToCelsius(weatherResponse.main!!.feels_like)) + " °C\n" +
                                "Wind speed: " +
                                weatherResponse.wind!!.speed + " m/s" + "\n" +
                                "Humidity: " +
                                weatherResponse.main!!.humidity + "\n" +
                                "Pressure: " +
                                weatherResponse.main!!.pressure + "\n" +
                                "Date: " +
                                date

                    weatherData = stringBuilder
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                weatherData = "No internet"
            }
        })
    }

    fun convertToCelsius(temperature: Float?): Float {
        return (temperature!! - 273)
    }

    companion object {
        var BaseUrl = "http://api.openweathermap.org/"
        var AppId = "d636b0449e92c5d6e65fb2c84735cfdd"
        var city_id = "1526384"
        var weatherData: String? = null

        const val EXTRA_REPLY = "com.example.android.weatherlistsql.REPLY"
    }
}