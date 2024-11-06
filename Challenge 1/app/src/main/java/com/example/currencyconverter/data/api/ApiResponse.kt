package com.example.currencyconverter.data.api

data class ApiResponse(
    val success: Boolean,
    val symbols: Map<String, String>
)

data class ExchangeRateResponse(
    val success: Boolean,
    val rates: Map<String, Double>
)
