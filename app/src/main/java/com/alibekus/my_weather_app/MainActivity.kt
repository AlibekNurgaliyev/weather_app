package com.alibekus.my_weather_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.alibekus.my_weather_app.API.WeatherResponse
import com.alibekus.my_weather_app.API.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

private var weatherData: TextView? = null

private lateinit var getDataButton: Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherData = findViewById(R.id.textView)
        getDataButton = findViewById(R.id.getButton)
        getDataButton.setOnClickListener {
            getCurrentWeatherData()
        }
    }

    internal fun getCurrentWeatherData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
//        val call = service.getCurrentWeatherData(lat, lon, AppId)
        val call = service.getCurrentWeatherData(city_id, AppId)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!
                    //val currentDateTime = LocalDateTime.now()
                    var simpleDateFormat :SimpleDateFormat
                    var date : String
                    var calendar : Calendar
                    calendar = Calendar.getInstance()
                    simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                    date = simpleDateFormat.format(calendar.time)
                    val stringBuilder = "Country: " +
                            weatherResponse.sys!!.country +
                            "\n" +
                            "Temperature: " +
                            weatherResponse.main!!.temp +
                            "\n" +
                            "Temperature(Min): " +
                            weatherResponse.main!!.temp_min +
                            "\n" +
                            "Temperature(Max): " +
                            weatherResponse.main!!.temp_max +
                            "\n" +
                            "Humidity: " +
                            weatherResponse.main!!.humidity +
                            "\n" +
                            "Pressure: " +
                            weatherResponse.main!!.pressure+
                            "\n" +
                            date

                    weatherData!!.text = stringBuilder
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                weatherData!!.text = "No internet"
            }
        })
    }


    companion object {
        var BaseUrl = "http://api.openweathermap.org/"
        var AppId = "2e65127e909e178d0af311a81f39948c"
        var city_id = "1526384"
//        var lat = "51.5074"
//        var lon = "0.1278"
    }
}