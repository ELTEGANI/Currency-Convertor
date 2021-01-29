package com.coin.currencyconverter.displaycurrency

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.coin.currencyconverter.R
import com.coin.currencyconverter.databinding.DisplayCurrencyRatesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DisplayCurrencyRatesFragment : Fragment() {

    private val displayCurrencyRatesViewModel: DisplayCurrencyRatesViewModel by viewModels()
    private lateinit var displayCurrencyRatesFragmentBinding: DisplayCurrencyRatesFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        displayCurrencyRatesFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.display_currency_rates_fragment, container, false)

        displayCurrencyRatesFragmentBinding.lifecycleOwner = this
        displayCurrencyRatesFragmentBinding.viewModel = displayCurrencyRatesViewModel

        displayCurrencyRatesFragmentBinding.spinner.setTitle("Select Currency")

        displayCurrencyRatesFragmentBinding.displayTn.setOnClickListener {
            displayCurrencyRatesViewModel.displayRates(displayCurrencyRatesFragmentBinding.amountTextView.text.toString())
        }

        displayCurrencyRatesViewModel.errorMsg.observe(viewLifecycleOwner, {
            if (it != null) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                displayCurrencyRatesViewModel.errorMsgDisplayed()
            }
        })

        return displayCurrencyRatesFragmentBinding.root

    }


}