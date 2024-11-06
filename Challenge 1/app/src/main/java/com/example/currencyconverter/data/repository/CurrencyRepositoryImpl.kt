package com.example.currencyconverter.data.repository

import com.example.currencyconverter.data.api.ApiService
import com.example.currencyconverter.data.model.Currency
import com.example.currencyconverter.domain.repository.CurrencyRepository

class CurrencyRepositoryImpl(private val apiService: ApiService) : CurrencyRepository {
    override suspend fun getSupportedCurrencies(): List<Currency> {
        val response = apiService.getSupportedCurrencies("810bac5bd7be107730fea49c93f3c76c")
        return response.symbols.map { Currency(it.key, it.value, it.value) } 
    }

    override suspend fun getExchangeRates(baseCurrency: String): Map<String, Double> {
        val response = apiService.getExchangeRates("810bac5bd7be107730fea49c93f3c76c", baseCurrency)
        return response.rates
    }
}