package com.coin.currencyconverter.database


data class NewRates(
    var id:Int,
    var currencyName:String,
    var currencyRates:Float,
    var ratesValue:Float
)