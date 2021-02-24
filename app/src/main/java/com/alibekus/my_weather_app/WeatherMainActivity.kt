package com.alibekus.my_weather_app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibekus.my_weather_app.database.Weather
import com.alibekus.my_weather_app.database.WeatherApplication
import com.alibekus.my_weather_app.viewModel.WeatherListAdapter
import com.alibekus.my_weather_app.viewModel.WeatherViewModel
import com.alibekus.my_weather_app.viewModel.WeatherViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WeatherMainActivity : AppCompatActivity() {

    private val newWeatherActivityRequestCode = 1
    private val weatherViewModel: WeatherViewModel by viewModels {
        WeatherViewModelFactory((application as WeatherApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WeatherListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        weatherViewModel.storedWeathers.observe(this, Observer { weather ->
            weather?.let { adapter.submitList(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@WeatherMainActivity, CitiesActivity::class.java)
            startActivityForResult(intent, newWeatherActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newWeatherActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(CitiesActivity.EXTRA_REPLY)?.let {
                val weather = Weather(it)
                weatherViewModel.insert(weather)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}