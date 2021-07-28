package com.example.taskymctaskface.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskymctaskface.data.local.model.Counter
import com.example.taskymctaskface.databinding.TextRowItemBinding

class MainActivityAdapter(
    private val listener: (counter : Counter) -> Unit)
    : ListAdapter<Counter, MainActivityAdapter.MainActivityViewHolder>(DiffCallback()) {
    private var adapterCounterList = listOf<Counter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
        return MainActivityViewHolder(
            TextRowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { listener.invoke(adapterCounterList[adapterPosition]) }
        }
    }

    override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun loadCounters(counterList: List<Counter>) {
        adapterCounterList = counterList
        notifyItemInserted(counterList.size - 1)
    }

    class MainActivityViewHolder(private val binding: TextRowItemBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(counter: Counter) = with(binding){
                textView.text = counter.number.toString()
            }
    }

    class DiffCallback : DiffUtil.ItemCallback<Counter>(){
        override fun areItemsTheSame(oldItem: Counter, newItem: Counter): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: Counter, newItem: Counter): Boolean {
            return oldItem == newItem
        }

    }
}
