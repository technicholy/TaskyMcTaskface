package com.example.taskymctaskface.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskymctaskface.data.local.model.Counter
import com.example.taskymctaskface.databinding.TextRowItemBinding

class MainActivityAdapter()
    : ListAdapter<Counter, MainActivityAdapter.MainActivityViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
        return MainActivityViewHolder(
            TextRowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
        holder.bind(getItem(position))
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
