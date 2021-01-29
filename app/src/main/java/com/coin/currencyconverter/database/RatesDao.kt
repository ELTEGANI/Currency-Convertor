package com.coin.currencyconverter.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface RatesDao {
    @Query("Select * From Rates where currencyAmount = currencyAmount * :usdAmount")
    fun getAllRates(usdAmount:Float): Flow<List<Rates>>

    @Query("Select currencyAmount from Rates where currencyName = :currency")
    suspend fun getCurrencyUsdValue(currency:String):Float

    @Insert
    suspend fun insertAllRates(rates: List<Rates>)
}