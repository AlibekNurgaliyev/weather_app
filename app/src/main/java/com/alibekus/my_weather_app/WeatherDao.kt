package com.alibekus.my_weather_app

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather_table")
    fun getStoredWeather(): Flow<List<Weather>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weather:Weather)

    @Query("DELETE FROM weather_table")
    suspend fun deleteAll()
}