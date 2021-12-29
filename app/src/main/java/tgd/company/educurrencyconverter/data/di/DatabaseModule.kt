package tgd.company.educurrencyconverter.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tgd.company.educurrencyconverter.data.cbr.local.room.CbrDatabase
import tgd.company.educurrencyconverter.data.cbr.local.room.CurrencyDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCbrDatabase(@ApplicationContext appContext: Context): CbrDatabase {
        return Room.databaseBuilder(
            appContext,
            CbrDatabase::class.java,
            "CbrDatabase.db"
        ).build()
    }

    @Provides
    fun provideCurrencyDao(cbrDatabase: CbrDatabase): CurrencyDao {
        return cbrDatabase.currencyDao()
    }
}