package com.coin.currencyconverter.displaycurrency

import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.coin.currencyconverter.database.NewRates
import com.coin.currencyconverter.database.Rates
import com.coin.currencyconverter.repository.RatesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

@ExperimentalCoroutinesApi
class DisplayCurrencyRatesViewModel @ViewModelInject constructor(private val ratesRepository: RatesRepository) : ViewModel() {


    init {
       viewModelScope.launch {
           getRatesFromApi()
       }
    }

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String>
        get() = _errorMsg

    private val _allRates = MutableLiveData<List<NewRates>>()
    val allRates: LiveData<List<NewRates>>
        get() = _allRates

    var currency:String? =null

    fun displayRates(amount:String){
        if (currency == "Select Currency") {
            _errorMsg.value = "Please Select Currency"
        } else if (amount.isEmpty()){
           _errorMsg.value = "Please Enter Currency Amount"
        }else {
           getCurrenciesRates("USD" + currency?.substring(range = 0..2),amount)
        }
    }

    fun getCurrenciesRates(currency:String, amount: String) {
        viewModelScope.launch {
            try {
                val currencyInUsd = amount.toFloat().div(ratesRepository.getUsdValue(currency))
                _allRates.value = ratesRepository.getListOfRates(currencyInUsd)
            }catch (exception:Exception){
                _errorMsg.value = "No Rates Available For This Currency"
            }
        }
    }

    fun inserRates(rates:List<Rates>){
        viewModelScope.launch {
            ratesRepository.insertRates(rates)
        }
    }

    fun onSelectCurrencyItem(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        currency = parent.selectedItem.toString()
    }

    fun errorMsgDisplayed(){
        _errorMsg.value = null
    }

    @ExperimentalCoroutinesApi
    fun getRatesFromApi() {
        viewModelScope.launch {
            ratesRepository.getRates()
                .onStart { }
                .onCompletion {}
                .catch {
                    if (it is IOException){
                        Log.d("IOException",it.toString())
                    }else if (it is HttpException){
                        Log.d("HttpException",it.toString())
                    }
                }.collect {
                    Log.d("Rates",it.toString())
                }
        }
    }

}