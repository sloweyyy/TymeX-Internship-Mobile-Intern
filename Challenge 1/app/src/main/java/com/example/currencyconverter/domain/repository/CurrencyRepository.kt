package com.example.currencyconverter.domain.repository

import com.example.currencyconverter.data.model.Currency

interface CurrencyRepository {
    suspend fun getSupportedCurrencies(): List<Currency>
    suspend fun getExchangeRates(baseCurrency: String): Map<String, Double>
}
