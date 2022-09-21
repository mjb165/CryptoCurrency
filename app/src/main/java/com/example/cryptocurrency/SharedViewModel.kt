package com.example.cryptocurrency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.data.Constant
import com.example.cryptocurrency.networking.model.MarketData
import com.example.cryptocurrency.networking.ApiService
import com.example.cryptocurrency.networking.RetrofitInstance
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SharedViewModel : ViewModel() {

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    private val _stocksData: MutableLiveData<MarketData?> = MutableLiveData()
    val stocksData: LiveData<MarketData?> = _stocksData

    private val _currenciesData: MutableLiveData<MarketData?> = MutableLiveData()
    val currenciesData: LiveData<MarketData?> = _currenciesData

    private val _optionsData: MutableLiveData<MarketData?> = MutableLiveData()
    val optionsData: LiveData<MarketData?> = _optionsData

    private val service = RetrofitInstance
        .getRetrofitInstance()
        .create(ApiService::class.java)

    /**
     * Get data from API
     */
    suspend fun getStocksData() {
        val response = withContext(viewModelScope.coroutineContext + dispatcher) {
            /**
             * Delay for chance to see loading screen
             */
            delay(1000)
            return@withContext service.getDailyStocks(Constant.API_KEY)
        }
        when (response.code()) {
            200 -> {
                _stocksData.value = response.body()
            }
            else -> {
                _stocksData.value = null
            }
        }
    }

    /**
     * Get data from API
     */
    suspend fun getCurrenciesData() {
        val response = withContext(viewModelScope.coroutineContext + dispatcher) {
            /**
             * Delay for chance to see loading screen
             */
            delay(1000)
            return@withContext service.getDailyCurrencies(Constant.API_KEY)
        }
        when (response.code()) {
            200 -> {
                _currenciesData.value = response.body()
            }
            else -> {
                _currenciesData.value = null
            }
        }
    }

    /**
     * Get data from API
     */
    suspend fun getOptionsData() {
        val response = withContext(viewModelScope.coroutineContext + dispatcher) {
            /**
             * Delay for chance to see loading screen
             */
            delay(1000)
            return@withContext service.getDailyOptions(Constant.API_KEY)
        }
        when (response.code()) {
            200 -> {
                _optionsData.value = response.body()
            }
            else -> {
                _optionsData.value = null
            }
        }
    }
}