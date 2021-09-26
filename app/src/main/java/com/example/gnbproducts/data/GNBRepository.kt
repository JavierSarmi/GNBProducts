package com.example.gnbproducts.data
import com.example.gnbproducts.data.network.GNBService
import com.example.gnbproducts.data.dto.RateDTO
import com.example.gnbproducts.data.dto.TransactionDTO
import javax.inject.Inject

/**
 * Created by Javier Sarmiento
 */
class GNBRepository @Inject constructor(
    private val api: GNBService,
    private val quoteProvider: GNBProvider
){

    suspend fun getRates(): List<RateDTO> {
        val response = api.getRates()
        quoteProvider.rates = response
        return response
    }

    suspend fun getTransactions(): List<TransactionDTO> {
        val response = api.getTransactions()
        quoteProvider.transactions = response
        return response
    }
}