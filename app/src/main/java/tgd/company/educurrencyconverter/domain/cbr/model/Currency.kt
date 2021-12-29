package tgd.company.educurrencyconverter.domain.cbr.model

data class Currency(
    val charCode: String,
    val name: String,
    val value: Double,
    val previous: Double,
    val nominal: Double
)
