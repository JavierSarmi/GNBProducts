package com.example.gnbproducts.domain.usecases

import com.example.gnbproducts.data.GNBRepository
import com.example.gnbproducts.domain.mappers.toRateModel
import javax.inject.Inject

/**
 * Created by Javier Sarmiento
 */
class RateUseCases @Inject constructor(private val repository: GNBRepository) {

    suspend operator fun invoke() = repository.getRates().toRateModel()

}