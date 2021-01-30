package com.coin.currencyconverter.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface RatesDao {
    @Query("Select id,currencyName,currencyRates,(currencyRates * :currencyInUsd) as ratesValue From Rates")
    suspend fun getAllRates(currencyInUsd: Float): List<NewRates>

    @Query("Select currencyRates from Rates where currencyName = :currency")
    suspend fun getCurrencyUsdValue(currency:String):Float

    @Insert
    suspend fun insertAllRates(rates: List<Rates>)
}