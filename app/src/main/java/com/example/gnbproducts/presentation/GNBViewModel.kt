package com.example.gnbproducts.presentation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gnbproducts.common.CurrencyUtils
import com.example.gnbproducts.domain.usecases.RateUseCases
import com.example.gnbproducts.domain.usecases.TransactionUseCases
import com.example.gnbproducts.domain.models.CurrencieModel
import com.example.gnbproducts.domain.models.TransactionsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GNBViewModel @Inject constructor(
    private val rateUseCases: RateUseCases,
    private val transactionUseCases: TransactionUseCases
) : ViewModel() {

    val rateList = MutableLiveData<Map<String, CurrencieModel>>()
    val transactionList = MutableLiveData<TransactionsModel>()
    val isLoading = MutableLiveData<Boolean>()

    fun getRates() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = rateUseCases()

            if (!CurrencyUtils.haveTotalRatesForCurrencies(result)) {
                rateList.postValue(CurrencyUtils.fillMissingRATES(result.toMutableMap()))
            } else {
                rateList.postValue(result)
            }
            /*if(!result.rateList.isNullOrEmpty()){
                rateList.postValue(result.rateList)
                isLoading.postValue(false)
            }*/
        }
    }

    fun getTransactions() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = transactionUseCases()

            if (!result.productList.isNullOrEmpty()) {
                transactionList.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

}