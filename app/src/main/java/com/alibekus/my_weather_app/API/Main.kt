package com.alibekus.my_weather_app.API

import com.google.gson.annotations.SerializedName

class Main {
    @SerializedName("temp")
    var temp: Float? = 0.toFloat()

    @SerializedName("humidity")
    var humidity: Float? = 0.toFloat()

    @SerializedName("pressure")
    var pressure: Float? = 0.toFloat()

    @SerializedName("temp_min")
    var temp_min: Float? = 0.toFloat()

    @SerializedName("temp_max")
    var temp_max: Float? = 0.toFloat()

    @SerializedName("feels_like")
    var feels_like: Float? = 0.toFloat()
}