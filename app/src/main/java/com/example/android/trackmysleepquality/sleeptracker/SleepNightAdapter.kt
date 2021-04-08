package com.example.android.trackmysleepquality.sleeptracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter : RecyclerView.Adapter<SleepNightAdapter.ViewHolder>() {

    //The adapter needs to let the RecyclerView know when the data has changed,
    //because the RecyclerView knows nothing about the data.
    //It only knows about the view holders that the adapter gives to it.
    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    //    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val view = layoutInflater
//                .inflate(R.layout.text_item_view, parent, false) as TextView
//        return TextItemViewHolder(view)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
                .inflate(R.layout.list_item_sleep_night,
                        parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        val res = holder.itemView.context.resources
        holder.sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
        holder.quality.text = convertNumericQualityToString(item.sleepQuality, res)
        holder.qualityImage.setImageResource(when (item.sleepQuality) {
            0 -> R.drawable.sleep_0
            1 -> R.drawable.sleep_1
            2 -> R.drawable.sleep_2
            3 -> R.drawable.sleep_3
            4 -> R.drawable.sleep_4
            5 -> R.drawable.sleep_5
            else -> R.drawable.sleep_0
        })
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        val quality: TextView = itemView.findViewById(R.id.quality_string)
        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)
    }
}