package com.coin.currencyconverter.database

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase


@Database(entities = [Rates::class],version = 1)
abstract  class RatesDataBase : RoomDatabase() {
    abstract fun RatesDao() : RatesDao
}