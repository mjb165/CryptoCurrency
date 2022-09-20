package com.example.cryptocurrency.module.dashboard.fragment

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.cryptocurrency.R
import com.example.cryptocurrency.databinding.FragmentDashboardBinding
import com.example.cryptocurrency.module.dashboard.adapter.DashboardAdapter

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        setDashboard()

        return binding.root
    }

    /**
     * Set active categories of the dashboard
     */
    private fun setDashboard() {
        viewPager = binding.publicTransportViewPager

        with(viewPager) {
            adapter = DashboardAdapter(this@DashboardFragment)

            setCurrentItem(1, false)

            offscreenPageLimit = 1

            val nextItemVisibleDp = resources.getDimension(R.dimen.pageMargin)
            val currentItemMarginDp = resources.getDimension(R.dimen.pageOffset)
            val pageTranslationX = nextItemVisibleDp + currentItemMarginDp

            setPageTransformer { page: View, position: Float ->
                page.translationX = -pageTranslationX * position
                page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
            }
            val itemDecoration = object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State,
                ) {
                    outRect.right = currentItemMarginDp.toInt()
                    outRect.left = currentItemMarginDp.toInt()
                }
            }
            addItemDecoration(itemDecoration)
        }
    }
}