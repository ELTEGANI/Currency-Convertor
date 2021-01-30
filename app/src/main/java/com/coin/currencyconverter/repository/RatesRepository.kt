package com.coin.currencyconverter.repository

import com.coin.currencyconverter.api.CurrencyConvertorService
import com.coin.currencyconverter.api.RatesResponse
import com.coin.currencyconverter.database.NewRates
import com.coin.currencyconverter.database.Rates
import com.coin.currencyconverter.database.RatesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class RatesRepository @Inject constructor(private val ratesDao: RatesDao,private var
        currencyConvertorService: CurrencyConvertorService){

    suspend fun getUsdValue(currency:String):Float{
        return ratesDao.getCurrencyUsdValue(currency)
    }

    suspend fun insertRates(rates: List<Rates>){
        return ratesDao.insertAllRates(rates)
    }

    suspend fun getListOfRates(currencyInUsd: Float): List<NewRates> = ratesDao.getAllRates(currencyInUsd)

    fun getRates(): Flow<RatesResponse> {
        return flow {
            val currencyConvertorService = currencyConvertorService.getRates()
            emit(currencyConvertorService)
        }.flowOn(Dispatchers.IO)
    }

}