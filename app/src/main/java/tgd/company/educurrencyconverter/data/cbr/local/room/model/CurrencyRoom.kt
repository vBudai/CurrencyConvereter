package tgd.company.educurrencyconverter.data.cbr.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import tgd.company.educurrencyconverter.data.cbr.model.CbrModel

@Entity(tableName = "currencies")
data class CurrencyRoom(
    @PrimaryKey
    val charCode: String,
    val name: String,
    val value: Double,
    val previous: Double,
    val nominal: Double
) : CbrModel()
