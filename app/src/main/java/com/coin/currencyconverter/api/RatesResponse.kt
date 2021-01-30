package com.coin.currencyconverter.api


import com.google.gson.annotations.SerializedName

data class RatesResponse(
    @SerializedName("privacy")
    val privacy: String,
    @SerializedName("quotes")
    val quotes: quotes,
    @SerializedName("source")
    val source: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("terms")
    val terms: String,
    @SerializedName("timestamp")
    val timestamp: Int
)