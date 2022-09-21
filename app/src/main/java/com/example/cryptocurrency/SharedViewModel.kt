package com.example.cryptocurrency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.data.Constant
import com.example.cryptocurrency.model.MarketData
import com.example.cryptocurrency.networking.ApiService
import com.example.cryptocurrency.networking.RetrofitInstance
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SharedViewModel : ViewModel() {

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    private val _marketData: MutableLiveData<MarketData?> = MutableLiveData()
    val marketData: LiveData<MarketData?> = _marketData

    private val service = RetrofitInstance
        .getRetrofitInstance()
        .create(ApiService::class.java)

    suspend fun getStocksData() {
        val response = withContext(viewModelScope.coroutineContext + dispatcher) {
            /**
             * Delay for chance to see loading screen
             */
            delay(3000)
            return@withContext service.getDailyStocks(Constant.API_KEY)
        }
        when (response.code()) {
            200 -> {
                _marketData.value = response.body()
            }
            else -> {
                _marketData.value = null
            }
        }
    }

    suspend fun getCurrenciesData() {
        val response = withContext(viewModelScope.coroutineContext + dispatcher) {
            /**
             * Delay for chance to see loading screen
             */
            delay(3000)
            return@withContext service.getDailyCurrencies(Constant.API_KEY)
        }
        when (response.code()) {
            200 -> {
                _marketData.value = response.body()
            }
            else -> {
                _marketData.value = null
            }
        }
    }
}