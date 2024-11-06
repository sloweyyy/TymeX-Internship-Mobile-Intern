package com.example.currencyconverter.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.usecases.GetExchangeRates
import com.example.currencyconverter.domain.usecases.GetSupportedCurrencies
import kotlinx.coroutines.launch

class MainViewModel(
    private val getSupportedCurrencies: GetSupportedCurrencies,
    private val getExchangeRates: GetExchangeRates
) : ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
    }

    private val _currencies = MutableLiveData<List<String>>()
    val currencies: LiveData<List<String>> = _currencies

    private val _exchangeRates = MutableLiveData<Map<String, Double>>()
    val exchangeRates: LiveData<Map<String, Double>> = _exchangeRates

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _convertedAmount = MutableLiveData<Double>()
    val convertedAmount: LiveData<Double> = _convertedAmount

    // Keep track of the current base currency
    private var currentBaseCurrency: String? = null

    fun loadCurrencies() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                Log.d(TAG, "Loading currencies")
                val currencies = getSupportedCurrencies()
                val currencyCodes = currencies.map { it.code }
                Log.d(TAG, "Received currencies: $currencyCodes")
                _currencies.value = currencyCodes
            } catch (e: Exception) {
                Log.e(TAG, "Error loading currencies", e)
                _error.value = "Failed to load currencies: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadExchangeRates() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                Log.d(TAG, "Loading exchange rates with EUR base")
                val rates = getExchangeRates("EUR") // Always get EUR-based rates
                Log.d(TAG, "Received rates: $rates")

                if (rates.isEmpty()) {
                    throw Exception("Received empty rates from API")
                }

                _exchangeRates.value = rates
            } catch (e: Exception) {
                Log.e(TAG, "Error loading exchange rates", e)
                _error.value = "Failed to load exchange rates: ${e.message}"
                _exchangeRates.value = emptyMap()
            } finally {
                _isLoading.value = false
            }
        }
    }




    fun convertCurrency(amount: Double, fromCurrency: String, toCurrency: String) {
        val rates = _exchangeRates.value ?: run {
            _error.value = "Exchange rates not available"
            return
        }

        if (fromCurrency == toCurrency) {
            _convertedAmount.value = amount
            return
        }

        try {
            when {
                fromCurrency == "EUR" -> {
                    // Direct conversion from EUR to target currency
                    val targetRate = rates[toCurrency] ?: throw Exception("Rate not found for $toCurrency")
                    _convertedAmount.value = amount * targetRate
                }
                toCurrency == "EUR" -> {
                    // Direct conversion to EUR
                    val sourceRate = rates[fromCurrency] ?: throw Exception("Rate not found for $fromCurrency")
                    _convertedAmount.value = amount / sourceRate
                }
                else -> {
                    // Convert through EUR as intermediate
                    val sourceRate = rates[fromCurrency] ?: throw Exception("Rate not found for $fromCurrency")
                    val targetRate = rates[toCurrency] ?: throw Exception("Rate not found for $toCurrency")
                    
                    // First convert to EUR, then to target currency
                    val amountInEur = amount / sourceRate
                    _convertedAmount.value = amountInEur * targetRate
                }
            }
            _error.value = null
        } catch (e: Exception) {
            _error.value = "Error during conversion: ${e.message}"
        }
    }
}