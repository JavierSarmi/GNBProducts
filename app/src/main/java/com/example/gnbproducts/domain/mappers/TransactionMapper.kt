package com.example.gnbproducts.domain.mappers

import com.example.gnbproducts.data.dto.TransactionDTO
import com.example.gnbproducts.domain.models.ProductModel
import com.example.gnbproducts.domain.models.TransactionsModel

/**
 * Created by Javier Sarmiento
 */

fun List<TransactionDTO>.toTransactionsModel(): TransactionsModel {
    val groupedList = groupBy { it.sku }
    return TransactionsModel(groupedList.map { ProductModel(it.key, it.value) })
}