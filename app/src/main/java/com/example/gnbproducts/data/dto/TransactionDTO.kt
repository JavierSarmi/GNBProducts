package com.example.gnbproducts.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by Javier Sarmiento
 */

data class TransactionDTO(
    @SerializedName("sku") val sku: String,
    @SerializedName("amount") val amount: Double,
    @SerializedName("currency") val currency: String
)
