package com.coin.currencyconverter.repository

import com.coin.currencyconverter.database.NewRates
import com.coin.currencyconverter.database.Rates
import com.coin.currencyconverter.database.RatesDao
import javax.inject.Inject


class RatesRepository @Inject constructor(private val ratesDao: RatesDao){

    suspend fun getUsdValue(currency:String):Float{
        return ratesDao.getCurrencyUsdValue(currency)
    }

    suspend fun insertRates(rates: List<Rates>){
        return ratesDao.insertAllRates(rates)
    }

    suspend fun getListOfRates(currencyInUsd: Float): List<NewRates> = ratesDao.getAllRates(currencyInUsd)


}