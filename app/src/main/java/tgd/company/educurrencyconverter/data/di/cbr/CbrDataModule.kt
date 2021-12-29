package tgd.company.educurrencyconverter.data.di.cbr

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import tgd.company.educurrencyconverter.data.cbr.local.room.CurrencyDao
import tgd.company.educurrencyconverter.data.cbr.repository.cbr.CbrRepositoryImpl
import tgd.company.educurrencyconverter.data.cbr.repository.model.DataModel
import tgd.company.educurrencyconverter.domain.cbr.repository.CbrRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CbrDataModule {

    @Singleton
    @Provides
    fun provideCbrRepository(
        retrofit: Retrofit,
        currencyDao: CurrencyDao
    ): CbrRepository<DataModel> = CbrRepositoryImpl(retrofit, currencyDao)
}