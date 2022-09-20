package com.example.cryptocurrency.networking

import com.example.cryptocurrency.module.stocks.model.StocksData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("/v2/aggs/grouped/locale/us/market/stocks/2020-10-14?adjusted=true")
    suspend fun getDailyStocks(@Header("Authorization") apiKey: String) : Response<StocksData>
}