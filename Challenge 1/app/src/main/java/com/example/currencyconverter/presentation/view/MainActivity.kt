package com.example.currencyconverter.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconverter.databinding.ActivityMainBinding
import com.example.currencyconverter.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupSpinners()
        setupConvertButton()

        viewModel.loadCurrencies()
    }

    private fun setupObservers() {
        viewModel.currencies.observe(this) { currencies ->
            Log.d(TAG, "Received currencies: $currencies")
            if (currencies.isNotEmpty()) {
                updateSpinners(currencies)
                viewModel.loadExchangeRates()
            }
        }

        viewModel.exchangeRates.observe(this) { rates ->
            Log.d(TAG, "Received exchange rates: $rates")
            if (rates.isEmpty()) {
                binding.convertedAmountTextView.text = "No exchange rates available"
                return@observe
            }
        }

        viewModel.convertedAmount.observe(this) { amount ->
            Log.d(TAG, "Received converted amount: $amount")
            binding.convertedAmountTextView.text = String.format("%.2f", amount)
        }

        viewModel.error.observe(this) { errorMsg ->
            errorMsg?.let {
                Log.e(TAG, "Error received: $it")
                binding.convertedAmountTextView.text = "Error: $it"
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.convertButton.isEnabled = !isLoading
        }
    }

    private fun setupSpinners() {
        binding.fromCurrencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCurrency = parent?.getItemAtPosition(position).toString()
                Log.d(TAG, "From currency selected: $selectedCurrency")
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    
        binding.toCurrencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d(TAG, "To currency selected: ${parent?.getItemAtPosition(position)}")
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun updateSpinners(currencies: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.fromCurrencySpinner.adapter = adapter
        binding.toCurrencySpinner.adapter = adapter

        // Set default selections
        binding.fromCurrencySpinner.setSelection(0)
        if (currencies.size > 1) {
            binding.toCurrencySpinner.setSelection(1)
        }
    }

    private fun setupConvertButton() {
        binding.convertButton.setOnClickListener {
            val amountStr = binding.amountEditText.text.toString()
            val amount = amountStr.toDoubleOrNull()

            if (amount == null) {
                binding.convertedAmountTextView.text = "Please enter a valid number"
                return@setOnClickListener
            }

            val fromCurrency = binding.fromCurrencySpinner.selectedItem?.toString()
            val toCurrency = binding.toCurrencySpinner.selectedItem?.toString()

            Log.d(TAG, "Converting $amount from $fromCurrency to $toCurrency")

            if (fromCurrency == null || toCurrency == null) {
                binding.convertedAmountTextView.text = "Please select currencies"
                return@setOnClickListener
            }

            // Check if we have exchange rates before converting
            val rates = viewModel.exchangeRates.value
            if (rates == null || rates.isEmpty()) {
                binding.convertedAmountTextView.text = "No exchange rates available"
                return@setOnClickListener
            }
            viewModel.convertCurrency(amount, fromCurrency, toCurrency)

        }
    }
}