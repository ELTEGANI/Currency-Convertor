package com.coin.currencyconverter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coin.currencyconverter.database.NewRates
import com.coin.currencyconverter.databinding.CurrencyViewItemBinding


class CurrencyRatesAdapter() : ListAdapter<NewRates, CurrencyRatesAdapter.CurrencyPropertyViewHolder>(DiffCallback) {

    class CurrencyPropertyViewHolder(private var binding: CurrencyViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newRates: NewRates) {
            binding.currency = newRates
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyPropertyViewHolder {
        return CurrencyPropertyViewHolder(CurrencyViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CurrencyPropertyViewHolder, position: Int) {
        val CurrencyProperty = getItem(position)
        holder.bind(CurrencyProperty)
    }


    companion object DiffCallback : DiffUtil.ItemCallback<NewRates>() {
        override fun areItemsTheSame(oldItem: NewRates, newItem: NewRates): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: NewRates, newItem: NewRates): Boolean {
            return oldItem.currencyName == newItem.currencyName
        }
    }

}