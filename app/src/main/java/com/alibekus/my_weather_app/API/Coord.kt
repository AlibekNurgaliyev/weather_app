package com.alibekus.my_weather_app.API

import com.google.gson.annotations.SerializedName

class Coord {
    @SerializedName("lon")
    var lon: Float? = 0.toFloat()

    @SerializedName("lat")
    var lat: Float? = 0.toFloat()
}
