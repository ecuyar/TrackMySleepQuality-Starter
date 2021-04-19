package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

//ListAdapter keeps track of the list for you and notifies the adapter when the list is updated.
class SleepNightAdapter(val clickListener: SleepNigthListener) : ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SleepNight, clickListener: SleepNigthListener) {
            binding.sleep = item

            // This call is an optimization that asks data binding to execute any pending bindings right away.
            // It's always a good idea to call executePendingBindings() when you use binding adapters in a RecyclerView,
            // because it can slightly speed up sizing the views.
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

//adding diffutil class for more functionality to recycler view
class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        //If the items have the same nightId, they are the same item, so return true. Otherwise, return false.
        //DiffUtil uses this test to help discover if an item was added, removed, or moved.
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        //check whether oldItem and newItem contain the same data; that is, whether they are equal
        return oldItem == newItem
    }
}

class SleepNigthListener(val clickListener: (sleepId: Long) -> Unit) {
    fun onClick(night: SleepNight) = clickListener(night.nightId)
}

sealed class DataItem {

    data class SleepNightItem(val sleepNight: SleepNight) : DataItem() {
        override val id = sleepNight.nightId
    }

    object Header : DataItem() {
        //Long.MIN_VALUE, which is a very, very small number (literally, -2 to the power of 63).
        //So, this will never conflict with any nightId in existence.
        override val id = Long.MIN_VALUE
    }

    abstract val id: Long
}
