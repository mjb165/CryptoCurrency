package com.example.cryptocurrency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cryptocurrency.data.Constant
import com.example.cryptocurrency.databinding.ActivityMainBinding
import com.example.cryptocurrency.module.dashboard.fragment.DashboardFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragment = DashboardFragment()
        val tag = Constant.DASHBOARD_FRAGMENT_TAG
        replaceFragment(fragment, tag)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.currencies -> {
                    (supportFragmentManager.findFragmentById(R.id.activityFrameLayout) as DashboardFragment)
                        .setCategory(0)
                    true
                }
                R.id.stocks -> {
                    (supportFragmentManager.findFragmentById(R.id.activityFrameLayout) as DashboardFragment)
                        .setCategory(1)
                    true
                }
                R.id.options -> {
                    (supportFragmentManager.findFragmentById(R.id.activityFrameLayout) as DashboardFragment)
                        .setCategory(2)
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    /**
     * Replace fragment in frame layout
     */
    private fun replaceFragment(fragment: Fragment, backStackName: String?) {
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.addToBackStack(backStackName)
        fragmentManager.replace(R.id.activityFrameLayout, fragment)
        fragmentManager.commit()
    }

    /**
     * Set bottom menu item from dashboard slide
     */
    fun setBottomMenuItem(item: Int) {
        binding.bottomNavigation.selectedItemId = item
    }
}