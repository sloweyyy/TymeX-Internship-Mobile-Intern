package com.example.currencyconverter.domain.usecases

import com.example.currencyconverter.data.model.Currency
import com.example.currencyconverter.domain.repository.CurrencyRepository

class GetSupportedCurrencies(private val repository: CurrencyRepository) {
    suspend operator fun invoke(): List<Currency> {
        return repository.getSupportedCurrencies()
    }
}