package com.example.cryptocurrency.module.dashboard.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cryptocurrency.module.currencies.fragment.CurrenciesFragment
import com.example.cryptocurrency.module.options.fragment.OptionsFragment
import com.example.cryptocurrency.module.stocks.fragment.StocksFragment

class DashboardAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CurrenciesFragment()
            1 -> StocksFragment()
            else -> OptionsFragment()
        }
    }
}