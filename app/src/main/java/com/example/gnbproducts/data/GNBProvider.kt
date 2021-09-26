package com.example.gnbproducts.data

import com.example.gnbproducts.data.dto.RateDTO
import com.example.gnbproducts.data.dto.TransactionDTO
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Javier Sarmiento
 */
@Singleton
class GNBProvider @Inject constructor(){
        var rates:List<RateDTO> = emptyList()
        var transactions: List<TransactionDTO> = emptyList()
}