package tgd.company.educurrencyconverter.utils

import tgd.company.educurrencyconverter.domain.cbr.model.Currency

fun currencyMath(item: Currency, mainValue: String): String {
    return if (mainValue.toDouble() != 0.0) {
        (
                (item.nominal / item.value)
                        * mainValue.toDouble()
                )
            .round(4)
            .toString()
    } else mainValue
}

fun Double.round(decimals: Int = 2): Double = "%.${decimals}f".format(this).replace(',', '.').toDouble()