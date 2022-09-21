package com.example.cryptocurrency.networking

import com.example.cryptocurrency.model.MarketData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("/v2/aggs/grouped/locale/us/market/stocks/2022-09-20?adjusted=true")
    suspend fun getDailyStocks(@Header("Authorization") apiKey: String) : Response<MarketData>

    @GET("/v2/aggs/grouped/locale/global/market/fx/2020-09-20?adjusted=true")
    suspend fun getDailyCurrencies(@Header("Authorization") apiKey: String) : Response<MarketData>
}