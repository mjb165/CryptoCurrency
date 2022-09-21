package com.example.cryptocurrency.networking.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("T")
    val T: String,
    @SerializedName("c")
    val c: Double,
    @SerializedName("h")
    val h: Double,
    @SerializedName("l")
    val l: Double,
    @SerializedName("n")
    val n: Int,
    @SerializedName("o")
    val o: Double,
    @SerializedName("t")
    val t: Long,
    @SerializedName("v")
    val v: Double,
    @SerializedName("vw")
    val vw: Double,
)