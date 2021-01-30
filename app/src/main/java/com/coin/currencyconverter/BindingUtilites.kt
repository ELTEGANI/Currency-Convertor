package com.coin.currencyconverter

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.coin.currencyconverter.database.NewRates


@BindingAdapter("currencyName")
fun TextView.setCurrencyName(newRates: NewRates?) {
    newRates?.let {
        text = newRates.currencyName.substring(range = 3..5)
    }
}


@SuppressLint("SetTextI18n")
@BindingAdapter("rateValue")
fun TextView.setRateValue(newRates: NewRates?) {
    newRates?.let {
        text = "%.4f".format(newRates.ratesValue)
    }
}