package com.coin.currencyconverter.displaycurrency

import android.view.View
import android.widget.AdapterView
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.coin.currencyconverter.database.Rates
import com.coin.currencyconverter.repository.RatesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DisplayCurrencyRatesViewModel @ViewModelInject constructor(private val ratesRepository: RatesRepository) : ViewModel() {

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String>
        get() = _errorMsg

    private val _allRates = MutableLiveData<List<Rates>>()
    val allRates: LiveData<List<Rates>>
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
           val currencyInUsd = amount.toFloat().div(ratesRepository.getUsdValue(currency))
          //  _allRates.value = ratesRepository.getListOfRates(currencyInUsd).asLiveData()
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
}