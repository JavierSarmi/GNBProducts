package com.example.gnbproducts.common

import kotlin.math.round

/**
 * Created by Javier Sarmiento
 */

//Redondea al número de decimales que le pasemos por parámetro
fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}