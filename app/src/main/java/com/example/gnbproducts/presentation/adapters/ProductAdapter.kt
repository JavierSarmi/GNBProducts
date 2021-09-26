package com.example.gnbproducts.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gnbproducts.R
import com.example.gnbproducts.common.Constants
import com.example.gnbproducts.common.CurrencyUtils
import com.example.gnbproducts.common.round
import com.example.gnbproducts.data.dto.TransactionDTO
import com.example.gnbproducts.domain.models.CurrencieModel
import com.example.gnbproducts.domain.models.ProductModel

/**
 * Created by Javier Sarmiento
 */

class ProductAdapter(
    private val context: Context?,
    private var productList: List<ProductModel>? = null
) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var rateList: Map<String, CurrencieModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_product, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = productList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        productList?.get(position)?.let { holder.bindInformation(it) }
    }

    fun updateData(procuctUpdateList: List<ProductModel>) {
        productList = procuctUpdateList
        notifyDataSetChanged()
    }

    fun initRateList(rateList: Map<String, CurrencieModel>) {
        this.rateList = rateList
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvProductTitle: TextView = itemView.findViewById(R.id.tvTransactionSKU)
        private val ivOpen: ImageView = itemView.findViewById(R.id.ivOpen)
        private val recyclerTransactions: RecyclerView = itemView.findViewById(R.id.rvTransactions)
        private val tvTotalAmount: TextView = itemView.findViewById(R.id.tvTotalAmount)
        private var transactionAdapter: TransactionAdapter? = null

        fun bindInformation(productModel: ProductModel) {
            tvProductTitle.text = productModel.sku
            setUpTransactionAdapter(productModel.transactionList)
            ivOpen.setOnClickListener {
                if (recyclerTransactions.isVisible) {
                    recyclerTransactions.visibility = GONE
                    ivOpen.rotation = 0f
                } else {
                    recyclerTransactions.visibility = VISIBLE
                    ivOpen.rotation = -180f
                }
            }
            tvTotalAmount.text = rateList?.let { it1 ->
                CurrencyUtils.getTotalAmountForTransaction(
                    productModel.transactionList,
                    it1
                ).round(2).toString() + Constants.EUR_CURRENCY
            }
        }

        private fun setUpTransactionAdapter(transactionList: List<TransactionDTO>) {
            if (transactionAdapter != null) {
                transactionAdapter?.updateData(transactionList)
            } else {
                transactionAdapter = TransactionAdapter(transactionList)
                recyclerTransactions.adapter = transactionAdapter
                recyclerTransactions.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}