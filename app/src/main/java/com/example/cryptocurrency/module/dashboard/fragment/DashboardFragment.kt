package com.example.cryptocurrency.module.dashboard.fragment

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.cryptocurrency.MainActivity
import com.example.cryptocurrency.R
import com.example.cryptocurrency.SharedViewModel
import com.example.cryptocurrency.databinding.FragmentDashboardBinding
import com.example.cryptocurrency.module.dashboard.adapter.DashboardAdapter
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPager: ViewPager2

    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        setDashboard()

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity!!.finish()
                }
            })

        return binding.root
    }

    /**
     * Set active categories of the dashboard
     */
    private fun setDashboard() {
        viewPager = binding.publicTransportViewPager

        with(viewPager) {
            adapter = DashboardAdapter(this@DashboardFragment)

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    when (position) {
                        0 -> {
                            (activity as MainActivity).setBottomMenuItem(R.id.currencies)
                            if (viewModel.currenciesData.value == null)
                                lifecycleScope.launch {
                                    viewModel.getCurrenciesData()
                                }
                        }
                        1 -> {
                            (activity as MainActivity).setBottomMenuItem(R.id.stocks)
                            if (viewModel.stocksData.value == null)
                                lifecycleScope.launch {
                                    viewModel.getStocksData()
                                }
                        }
                        2 -> {
                            (activity as MainActivity).setBottomMenuItem(R.id.options)
                            if (viewModel.optionsData.value == null)
                                lifecycleScope.launch {
                                    viewModel.getOptionsData()
                                }
                        }
                    }
                }
            })
            (activity as MainActivity).setBottomMenuItem(R.id.stocks)
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
            setCurrentItem(1, false)
        }
    }

    /**
     * Set category in adapter
     */
    fun setCategory(item: Int) {
        viewPager.setCurrentItem(item, true)
    }
}