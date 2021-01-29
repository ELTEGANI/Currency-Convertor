package com.coin.currencyconverter.repository

import androidx.lifecycle.LiveData
import com.coin.currencyconverter.database.Rates
import com.coin.currencyconverter.database.RatesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RatesRepository @Inject constructor(private val ratesDao: RatesDao){

    suspend fun getUsdValue(currency:String):Float{
        return ratesDao.getCurrencyUsdValue(currency)
    }

    suspend fun insertRates(rates: List<Rates>){
        return ratesDao.insertAllRates(rates)
    }

    fun getListOfRates(currencyInUsd:Float) : Flow<List<Rates>> {
        return ratesDao.getAllRates(currencyInUsd)
    }


}