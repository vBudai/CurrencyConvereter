package tgd.company.educurrencyconverter.data.cbr.local.room

import androidx.room.*
import tgd.company.educurrencyconverter.data.cbr.local.room.model.CurrencyRoom

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM  currencies order by name DESC")
    fun getAll(): List<CurrencyRoom>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(currencies: List<CurrencyRoom>)

    @Delete
    fun delete(currency: CurrencyRoom)

    @Query("DELETE FROM currencies")
    fun deleteAll()
}