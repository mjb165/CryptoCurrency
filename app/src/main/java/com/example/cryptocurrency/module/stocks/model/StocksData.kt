package com.example.cryptocurrency.module.stocks.model

import com.google.gson.annotations.SerializedName

data class StocksData(
    @SerializedName("adjusted")
    val adjusted: Boolean,
    @SerializedName("queryCount")
    val queryCount: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("resultsCount")
    val resultsCount: Int,
    @SerializedName("status")
    val status: String
)