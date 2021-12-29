package tgd.company.educurrencyconverter.domain.cbr.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import tgd.company.educurrencyconverter.data.cbr.repository.model.DataModel
import tgd.company.educurrencyconverter.domain.cbr.interactor.CbrCurrencyInteractor
import tgd.company.educurrencyconverter.domain.cbr.interactor.CbrCurrencyInteractorImpl
import tgd.company.educurrencyconverter.domain.cbr.repository.CbrRepository
import tgd.company.educurrencyconverter.domain.model.DomainModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CbrDomainModule {

    @Singleton
    @Provides
    fun provideCbrCurrencyInteractor(
        cbrRepository: CbrRepository<DataModel>
    ): CbrCurrencyInteractor<DomainModel> = CbrCurrencyInteractorImpl(cbrRepository)
}