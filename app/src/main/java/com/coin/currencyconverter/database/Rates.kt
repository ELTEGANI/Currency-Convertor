package com.coin.currencyconverter.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Rates (
        var currencyName:String,
        var quotes:Double,
        @PrimaryKey(autoGenerate = true)
        var id:Int=0,
        )