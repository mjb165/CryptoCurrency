package com.example.cryptocurrency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.cryptocurrency.data.Constant
import com.example.cryptocurrency.module.dashboard.fragment.DashboardFragment
import com.example.cryptocurrency.module.stocks.model.StocksData
import com.example.cryptocurrency.networking.ApiService
import com.example.cryptocurrency.networking.RetrofitInstance
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = DashboardFragment()
        val tag = Constant.DASHBOARD_FRAGMENT_TAG
        replaceFragment(
            fragment,
            tag,
            null,
            null,
            null,
            null
        )

        val retService = RetrofitInstance
            .getRetrofitInstance()
            .create(ApiService::class.java)
        val responseLiveData: LiveData<Response<StocksData>> = liveData {
            val response = retService.getDailyStocks(Constant.API_KEY)
            emit(response)
        }

        responseLiveData.observe(this) {
            Log.d("DATA", it.body().toString())
        }
    }

    private fun replaceFragment(
        fragment: Fragment,
        backStackName: String?,
        animEnter: Int?,
        animExit: Int?,
        animPopEnter: Int?,
        animPopExit: Int?,
    ) {
        val fragmentManager = supportFragmentManager.beginTransaction()
        if (animEnter != null && animExit != null && animPopEnter != null && animPopExit != null) {
            fragmentManager.setCustomAnimations(
                animEnter,
                animExit,
                animPopEnter,
                animPopExit
            )
        }
        fragmentManager.addToBackStack(backStackName)
        fragmentManager.replace(R.id.activityFrameLayout, fragment)
        fragmentManager.commit()
    }
}