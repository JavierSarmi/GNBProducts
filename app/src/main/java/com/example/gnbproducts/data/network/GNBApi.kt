package com.example.gnbproducts.data.network

import com.example.gnbproducts.data.dto.RateDTO
import com.example.gnbproducts.data.dto.TransactionDTO
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Javier Sarmiento
 */
private const val GET_RATES = "rates.json"
private const val GET_TRANSACTIONS = "transactions.json"


interface GNBApi {

    @GET(GET_RATES)
    suspend fun getRates(): Response<List<RateDTO>>

    @GET(GET_TRANSACTIONS)
    suspend fun getTransactions(): Response<List<TransactionDTO>>
}