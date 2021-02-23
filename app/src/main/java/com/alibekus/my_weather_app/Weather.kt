package com.alibekus.my_weather_app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
class Weather(
    @PrimaryKey
    @ColumnInfo(name = "weather")
    val weather: String
)
