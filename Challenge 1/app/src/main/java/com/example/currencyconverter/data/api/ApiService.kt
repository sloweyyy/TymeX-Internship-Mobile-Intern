package com.example.currencyconverter.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("symbols")
    suspend fun getSupportedCurrencies(@Query("access_key") apiKey: String): ApiResponse

    @GET("latest")
    suspend fun getExchangeRates(@Query("access_key") apiKey: String, @Query("base") base: String): ExchangeRateResponse
}
