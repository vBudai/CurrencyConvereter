package tgd.company.educurrencyconverter.data.cbr.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import tgd.company.educurrencyconverter.data.cbr.local.room.model.CurrencyRoom

@Database(
    entities = [CurrencyRoom::class],
    version = 1,
    exportSchema = false
)
abstract class CbrDatabase: RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}