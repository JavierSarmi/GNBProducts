package com.example.gnbproducts.domain.usecases
import com.example.gnbproducts.data.GNBRepository
import com.example.gnbproducts.domain.mappers.toTransactionsModel
import javax.inject.Inject

/**
 * Created by Javier Sarmiento
 */
class TransactionUseCases @Inject constructor(private val repository: GNBRepository){

    suspend operator fun invoke() = repository.getTransactions().toTransactionsModel()

}