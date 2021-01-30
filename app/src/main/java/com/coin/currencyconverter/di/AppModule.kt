package com.coin.currencyconverter.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.coin.currencyconverter.api.CurrencyConvertorService
import com.coin.currencyconverter.database.RatesDao
import com.coin.currencyconverter.database.RatesDataBase
import com.coin.currencyconverter.repository.RatesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): RatesDataBase {
        return Room
                .databaseBuilder(context,RatesDataBase::class.java, "rates_database.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    fun provideRatesDao(ratesDataBase: RatesDataBase): RatesDao {
        return ratesDataBase.RatesDao()
    }

    @Singleton
    @Provides
    fun provideCurrencyRatesService(): CurrencyConvertorService {
        return CurrencyConvertorService.create()
    }

}