package com.alibekus.my_weather_app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(WeatherDB::class), version = 1, exportSchema = false)
abstract class WeatherRoomDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherRoomDatabase? = null

        fun getDatabase(context: Context): WeatherRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherRoomDatabase::class.java,
                    "weather_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}