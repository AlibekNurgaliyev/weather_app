package com.alibekus.my_weather_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class WeatherListAdapter :
    androidx.recyclerview.widget.ListAdapter<WeatherDB, WordViewHolder>(WordComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.weather)
    }
}


class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val wordItemView: TextView = itemView.findViewById(R.id.textView)

    fun bind(text: String?) {
        wordItemView.text = text
    }

    companion object {
        fun create(parent: ViewGroup): WordViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
            return WordViewHolder(view)
        }
    }
}

class WordComparator : DiffUtil.ItemCallback<WeatherDB>() {
    override fun areItemsTheSame(oldItem: WeatherDB, newItem: WeatherDB): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: WeatherDB, newItem: WeatherDB): Boolean {
        return oldItem.weather == newItem.weather
    }
}