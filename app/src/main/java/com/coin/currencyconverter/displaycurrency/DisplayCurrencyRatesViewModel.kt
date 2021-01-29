package com.coin.currencyconverter.displaycurrency

import android.view.View
import android.widget.AdapterView
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coin.currencyconverter.R

class DisplayCurrencyRatesViewModel @ViewModelInject constructor() : ViewModel() {

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String>
        get() = _errorMsg

    var currency:String? =null

    fun displayRates(amount:String){
        if (currency == "Select Currency") {
            _errorMsg.value = "Please Select Currency"
        } else if (amount.isEmpty()){
           _errorMsg.value = "Please Enter Currency Amount"
        }else{
           _errorMsg.value = amount +" "+currency?.substring(range = 0..2)
        }
    }


    fun onSelectCurrencyItem(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        currency = parent.selectedItem.toString()
    }

    fun errorMsgDisplayed(){
        _errorMsg.value = null
    }
}