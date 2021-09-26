package com.example.gnbproducts.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by Javier Sarmiento
 */

data class RateDTO(
    @SerializedName("from") val from: String,
    @SerializedName("to") val to: String,
    @SerializedName("rate") val rate: Double
)
