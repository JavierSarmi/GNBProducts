package com.example.gnbproducts.common

import android.widget.TextView
import com.example.gnbproducts.data.dto.RateDTO
import com.example.gnbproducts.data.dto.TransactionDTO
import com.example.gnbproducts.domain.models.CurrencieModel

/**
 * Created by Javier Sarmiento
 */
object CurrencyUtils {
    /*
     * Método que calcula si tenemos el total de conversores mediante la fórmula de combinatioria n*(n-1)
     * Por un lado se calcula el total de monedas (n)
     * Por otro lado se calcula el total de conversores que ya tenemos y se compara contra el resultado
     * obtenido en la fórmula
     */
    fun haveTotalRatesForCurrencies(conversorList: Map<String, CurrencieModel>): Boolean {
        val currencyCounterList: Map<String, Int> =
            conversorList.mapValues { (_, currency) -> currency.currencyMap.count() }
        val totalOfCurrencies = currencyCounterList.size
        val totalOfConversions = currencyCounterList.toList().sumBy { it.second }
        return totalOfCurrencies * (totalOfCurrencies - 1) == totalOfConversions
    }

    /*
     * Método que recorre los conversores para detectar los faltantes y calcularlos
     */
    //TODO- Cambiar nombres de variables para que quede más claro el flujo de la función
    fun fillMissingRATES(conversorList: MutableMap<String, CurrencieModel>): MutableMap<String, CurrencieModel> {
        val currencys = conversorList.keys

        val conversorsToAdd = mutableListOf<RateDTO>()
        //Recorre las currencies (EUR, USD ...)
        currencys.forEach currencies@{

            //Entramos dentro de cada currency para ver los pares, no se contemplan pares iguales
            conversorList.forEach currencyList@{ (currency, currencyConversorMap) ->
                //Currencies iguales no se contemplan (EUR/EUR)
                if (it == currency) {
                    return@currencyList
                }
                //Comprobamos que exitan todos los pares y en caso negativo lo calculamos
                var findCurrency = false
                currencyConversorMap.currencyMap.forEach conversor@{ conversor ->
                    if (it == conversor.key) {
                        findCurrency = true
                    }
                }
                if (!findCurrency) {
                    calculateCurrency(it, currency, conversorList)?.let { rate ->
                        conversorsToAdd.add(RateDTO(currency, it, rate))
                    }
                }
            }
        }
        for (rate in conversorsToAdd) {
            conversorList[rate.from]?.currencyMap?.put(rate.to, rate.rate)
        }

        if (haveTotalRatesForCurrencies(conversorList)) {
            return conversorList
        } else {
            fillMissingRATES(conversorList)
        }
        return conversorList
    }

    //Obtiene un coversor a partir de 2 existentes Ej: EUR/USD y USD/CAD -> EUR/CAD
    //TODO- Cambiar nombres de variables para que quede más claro el flujo de la función
    private fun calculateCurrency(
        from: String, to: String, currenciesList: Map<String, CurrencieModel>
    ): Double? {
        //Lista de las currencies "from" que disponemos
        val currenciesToCheck = currenciesList[from]
        currenciesToCheck?.currencyMap?.forEach { currency ->
            //Recorremos la lista de la currency en la que estamos en busca del conversor
            currenciesList[currency.key]?.currencyMap?.forEach {
                //Comprobamos si alguna de estas currencies tiene conversor a la currency que necesitamos
                if (it.key == to) {
                    return currenciesList[from]?.currencyMap?.get(currency.key)?.times((it.value))?.round(2)
                }
            }
        }
        return null
    }

    //Realiza la conversión de los "amount" de cada transacción y los suma todos
    fun getTotalAmountForTransaction(
        transactionList: List<TransactionDTO>,
        rateList: Map<String, CurrencieModel>
    ): Double {
        var totalAmount = 0.0
        for (transaction in transactionList) {
            totalAmount += transaction.amount * (rateList[transaction.currency]?.currencyMap?.get(Constants.EUR_CURRENCY)
                ?: 0.0)
        }
        return totalAmount
    }

}