package com.example.cryptocurrency.module.stocks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency.R
import com.example.cryptocurrency.model.MarketData

class StocksRecyclerAdapter(
    private val marketData: MarketData,
) : RecyclerView.Adapter<StocksRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stockSymbol: TextView = view.findViewById(R.id.stockSymbolTextView)
        val openPrice: TextView = view.findViewById(R.id.openPriceTextView)
        val closePrice: TextView = view.findViewById(R.id.closePriceTextView)
        val highPrice: TextView = view.findViewById(R.id.highPriceTextView)
        val lowPrice: TextView = view.findViewById(R.id.lowPriceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.stocks_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stock = marketData.results[position]

        holder.stockSymbol.text = stock.T
        holder.openPrice.text = stock.o.toString()
        holder.closePrice.text = stock.c.toString()
        holder.highPrice.text = stock.h.toString()
        holder.lowPrice.text = stock.l.toString()
    }

    override fun getItemCount() = marketData.queryCount
}