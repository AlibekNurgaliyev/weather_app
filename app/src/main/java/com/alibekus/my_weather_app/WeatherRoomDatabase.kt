package com.alibekus.my_weather_app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Weather::class), version = 1, exportSchema = false)
abstract class WeatherRoomDatabase : RoomDatabase() {
    abstract fun weatherdDao(): WeatherDao

    private class WeatherDatabaseCallback(
        private val scope: CoroutineScope
    ):RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let{database ->
                scope.launch {
                    populateDatabase(database.weatherdDao())
                }
            }
        }
        suspend fun populateDatabase(weatherDao: WeatherDao){
            weatherDao.deleteAll()

            var weather = Weather("Hello")
            weatherDao.insert( weather)
            weather = Weather("Alibekus!")
            weatherDao.insert(weather)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: WeatherRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WeatherRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherRoomDatabase::class.java,
                    "weather_databsase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

