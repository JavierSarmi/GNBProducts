package com.example.gnbproducts.data.network

import com.example.gnbproducts.data.dto.RateDTO
import com.example.gnbproducts.data.dto.TransactionDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Javier Sarmiento
 */
class GNBService @Inject constructor(private val api:GNBApi){

    suspend fun getRates(): List<RateDTO> {
        return withContext(Dispatchers.IO) {
            val response = api.getRates()
            response.body() ?: emptyList()
        }
    }

    suspend fun getTransactions(): List<TransactionDTO> {
        return withContext(Dispatchers.IO) {
            val response = api.getTransactions()
            response.body() ?: emptyList()
        }
    }
}