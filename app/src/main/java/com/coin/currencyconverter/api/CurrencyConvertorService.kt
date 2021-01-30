package com.coin.currencyconverter.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface CurrencyConvertorService {

    @GET("/live?access_key=24d3fe380168855c1b30a9a1a2bb3333")
    suspend fun getRates(): RatesResponse

    companion object {
        private const val BASE_URL = "http://api.currencylayer.com"

        fun create(): CurrencyConvertorService {
            val logger = HttpLoggingInterceptor().apply { level =
                    HttpLoggingInterceptor.Level.BASIC
            }
            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(CurrencyConvertorService::class.java)
        }
    }
}