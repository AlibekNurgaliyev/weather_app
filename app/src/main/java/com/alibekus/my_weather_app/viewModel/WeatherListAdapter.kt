package com.alibekus.my_weather_app.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alibekus.my_weather_app.R
import com.alibekus.my_weather_app.database.Weather

class WeatherListAdapter :
    androidx.recyclerview.widget.ListAdapter<Weather, WeatherViewHolder>(ItemComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.weather)
    }
}

class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val weatherItemView: TextView = itemView.findViewById(R.id.textView)

    fun bind(text: String?) {
        weatherItemView.text = text
    }

    companion object {
        fun create(parent: ViewGroup): WeatherViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
            return WeatherViewHolder(view)
        }
    }
}

class ItemComparator : DiffUtil.ItemCallback<Weather>() {
    override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem.weather == newItem.weather
    }
}