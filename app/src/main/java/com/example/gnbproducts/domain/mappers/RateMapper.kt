package com.example.gnbproducts.domain.mappers

import com.example.gnbproducts.data.dto.RateDTO
import com.example.gnbproducts.domain.models.CurrencieModel

/**
 * Created by Javier Sarmiento
 */

fun List<RateDTO>.toRateModel(): Map<String, CurrencieModel> {
    return this.groupBy { it.from }
        .map { rateDTO ->
            rateDTO.key to CurrencieModel(rateDTO.value.map { it.to to it.rate }
                .toMap().toMutableMap())
        }
        .toMap().toMutableMap()
}