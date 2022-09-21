package com.example.cryptocurrency.module.options.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.cryptocurrency.R
import com.example.cryptocurrency.data.Constant
import com.example.cryptocurrency.databinding.FragmentOptionsBinding

class OptionsFragment : Fragment() {

    private var _binding: FragmentOptionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOptionsBinding.inflate(inflater, container, false)
        getLogo()

        return binding.root
    }

    /**
     * Get logo from URL and set to imageView
     */
    private fun getLogo() {
        setProgressLayout(true, getString(R.string.data_loading))
        Glide.with(this).load(Constant.OPTIONS_LOGO_URL).into(binding.optionsImageView)
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
}