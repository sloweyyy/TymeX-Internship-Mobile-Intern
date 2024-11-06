package com.example.currencyconverter.domain.usecases

import com.example.currencyconverter.domain.repository.CurrencyRepository

class GetExchangeRates(private val repository: CurrencyRepository) {
    suspend operator fun invoke(baseCurrency: String): Map<String, Double> {
        return repository.getExchangeRates(baseCurrency)
    }
}