package com.coin.currencyconverter.displaycurrency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.coin.currencyconverter.R
import com.coin.currencyconverter.adapter.CurrencyRatesAdapter
import com.coin.currencyconverter.database.Rates
import com.coin.currencyconverter.databinding.DisplayCurrencyRatesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class DisplayCurrencyRatesFragment : Fragment() {

    @ExperimentalCoroutinesApi
    private val displayCurrencyRatesViewModel: DisplayCurrencyRatesViewModel by viewModels()
    private lateinit var displayCurrencyRatesFragmentBinding: DisplayCurrencyRatesFragmentBinding
    @ExperimentalCoroutinesApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        displayCurrencyRatesFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.display_currency_rates_fragment, container, false)

        displayCurrencyRatesFragmentBinding.lifecycleOwner = this
        displayCurrencyRatesFragmentBinding.viewModel = displayCurrencyRatesViewModel

        displayCurrencyRatesFragmentBinding.spinner.setTitle("Select Currency")

        displayCurrencyRatesFragmentBinding.displayTn.setOnClickListener {
            displayCurrencyRatesViewModel.displayRates(displayCurrencyRatesFragmentBinding.amountTextView.text.toString())
        }

        displayCurrencyRatesViewModel.errorMsg.observe(viewLifecycleOwner, {
            if (it != null) {
                Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                displayCurrencyRatesViewModel.errorMsgDisplayed()
            }
        })

        val currencyRatesAdapter = CurrencyRatesAdapter()
        displayCurrencyRatesFragmentBinding.currencyList.itemAnimator = DefaultItemAnimator()
        displayCurrencyRatesFragmentBinding.currencyList.adapter = currencyRatesAdapter
        subscribeUi(currencyRatesAdapter)


        //TODO remove it after get 30m updates using worker manger
        displayCurrencyRatesViewModel.inserRates(listOf(
                Rates("USDAED", 3.673202),
                Rates("USDAFN", 78.230239),
                Rates("USDALL", 102.222938))
        )

        return displayCurrencyRatesFragmentBinding.root

    }
    @ExperimentalCoroutinesApi
    private fun subscribeUi(adapter: CurrencyRatesAdapter) {
        displayCurrencyRatesViewModel.allRates.observe(viewLifecycleOwner) { allRates ->
            adapter.submitList(allRates)
        }
    }

}