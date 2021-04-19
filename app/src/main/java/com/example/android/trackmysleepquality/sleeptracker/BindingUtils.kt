package com.example.android.trackmysleepquality.sleeptracker

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

//Each function is called "extension function"
//This function will be your adapter for calculating and formatting the sleep duration.
//To tell data binding about this binding adapter, annotate the function with @BindingAdapter.
//This function is the adapter for the sleepDurationFormatted attribute, so pass sleepDurationFormatted as an argument to @BindingAdapter.
@BindingAdapter("sleepDurationFormatted")
fun TextView.setSleepDurationFormatted(item: SleepNight?) {
    item?.let {
        text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, context.resources)
    }
}

@BindingAdapter("sleepQualityString")
fun TextView.setSleepQualityString(item: SleepNight?) {
    item?.let {
        text = convertNumericQualityToString(item.sleepQuality, context.resources)
    }
}

@BindingAdapter("sleepImage")
fun ImageView.setSleepImage(item: SleepNight?) {
    item?.let {
        setImageResource(when (item.sleepQuality) {
            0 -> R.drawable.sleep_0
            1 -> R.drawable.sleep_1
            2 -> R.drawable.sleep_2
            3 -> R.drawable.sleep_3
            4 -> R.drawable.sleep_4
            5 -> R.drawable.sleep_5
            else -> R.drawable.sleep_0
        })
    }
}

