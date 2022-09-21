package com.example.cryptocurrency.networking

import com.example.cryptocurrency.networking.model.MarketData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("/v2/aggs/grouped/locale/us/market/stocks/2022-09-20?adjusted=true")
    suspend fun getDailyStocks(@Header("Authorization") apiKey: String) : Response<MarketData>

    @GET("/v2/aggs/grouped/locale/global/market/fx/2020-10-14?adjusted=true")
    suspend fun getDailyCurrencies(@Header("Authorization") apiKey: String) : Response<MarketData>

    @GET("/v2/aggs/ticker/O:TSLA210903C00700000/range/1/day/2021-07-01/2021-07-22?adjusted=true")
    suspend fun getDailyOptions(@Header("Authorization") apiKey: String) : Response<MarketData>
}