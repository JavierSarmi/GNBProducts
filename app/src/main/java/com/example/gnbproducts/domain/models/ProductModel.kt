package com.example.gnbproducts.domain.models

import com.example.gnbproducts.data.dto.TransactionDTO

/**
 * Created by Javier Sarmiento
 */
data class ProductModel(val sku: String, val transactionList: List<TransactionDTO>)