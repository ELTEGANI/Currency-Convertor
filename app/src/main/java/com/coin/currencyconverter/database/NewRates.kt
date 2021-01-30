package com.coin.currencyconverter.database


data class NewRates(
    var currencyName:String,
    var quotes:Float,
    var ratesValue:Float
)