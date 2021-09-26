package com.example.gnbproducts.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gnbproducts.R
import com.example.gnbproducts.data.dto.TransactionDTO

/**
 * Created by Javier Sarmiento
 */
class TransactionAdapter(private var transactionList: List<TransactionDTO>) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_transaction, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindInformation(transactionList[position])
    }

    fun updateData(transactionUpdateList: List<TransactionDTO>) {
        transactionList = transactionUpdateList
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
        private val tvCurrency: TextView = itemView.findViewById(R.id.tvCurrency)

        fun bindInformation(transaction: TransactionDTO) {
            //TODO- Redondear
            tvAmount.text = transaction.amount.toString()
            tvCurrency.text = transaction.currency
        }
    }
}