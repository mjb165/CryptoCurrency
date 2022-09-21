package com.example.cryptocurrency.module.currencies.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptocurrency.R
import com.example.cryptocurrency.SharedViewModel
import com.example.cryptocurrency.data.Constant
import com.example.cryptocurrency.databinding.FragmentCurrenciesBinding
import com.example.cryptocurrency.networking.model.MarketData
import com.example.cryptocurrency.module.adapter.MarketRecyclerAdapter

class CurrenciesFragment : Fragment() {

    private var _binding: FragmentCurrenciesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SharedViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView

    private lateinit var marketData: MarketData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCurrenciesBinding.inflate(inflater, container, false)

        setRecyclerView()
        getLogo()

        viewModel.currenciesData.observe(viewLifecycleOwner) { currenciesData ->
            if (currenciesData != null) {
                this.marketData = currenciesData
                with(binding) {
                    currenciesImageView.visibility = View.GONE
                    currenciesTextView.visibility = View.GONE
                    dividerView.visibility = View.GONE

                    infoTextView.visibility = View.VISIBLE
                    entryImageButton.visibility = View.VISIBLE
                }
                setProgressLayout(false, null)
            } else
                Toast.makeText(requireContext(),
                    getString(R.string.request_failed_message),
                    Toast.LENGTH_LONG
                ).show()
        }

        binding.entryImageButton.setOnClickListener {
            with(binding) {
                infoTextView.visibility = View.GONE
                entryImageButton.visibility = View.GONE

                recyclerView.adapter = MarketRecyclerAdapter(marketData)
                recyclerView.visibility = View.VISIBLE
            }
        }

        return binding.root
    }

    /**
     * Get logo from URL and set to imageView
     */
    private fun getLogo() {
        setProgressLayout(true, getString(R.string.data_loading))
        Glide.with(this).load(Constant.CURRENCIES_LOGO_URL).into(binding.currenciesImageView)
    }

    /**
     * Set the progress bar until the data is not loaded
     */
    private fun setProgressLayout(show: Boolean, text: String?) {
        val progressLayout = binding.progressLayout.progressLayout
        val textView = binding.progressLayout.progressTextView

        if (show) {
            progressLayout.visibility = View.VISIBLE
            if (text != null)
                textView.text = text
        } else
            progressLayout.visibility = View.GONE
    }

    /**
     * Set initial settings
     */
    private fun setRecyclerView() {
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.divider_recycler_view
            )!!
        )
        recyclerView = binding.dataRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.addItemDecoration(divider)
    }
}