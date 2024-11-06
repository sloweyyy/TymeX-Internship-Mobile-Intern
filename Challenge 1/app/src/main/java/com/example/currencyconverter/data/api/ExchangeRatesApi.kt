package com.example.currencyconverter.api

import com.example.currencyconverter.data.api.ApiResponse
import com.example.currencyconverter.data.api.ExchangeRateResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRatesApi {
    @GET("symbols")
    suspend fun getSupportedCurrencies(@Query("access_key") apiKey: String): ApiResponse

    @GET("latest")
    suspend fun getExchangeRates(@Query("access_key") apiKey: String, @Query("base") base: String): ExchangeRateResponse
}
