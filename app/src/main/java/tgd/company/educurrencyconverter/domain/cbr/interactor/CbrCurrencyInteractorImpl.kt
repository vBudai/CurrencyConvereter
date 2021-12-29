package tgd.company.educurrencyconverter.domain.cbr.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import tgd.company.educurrencyconverter.data.cbr.repository.model.DataModel
import tgd.company.educurrencyconverter.domain.cbr.model.Currency
import tgd.company.educurrencyconverter.domain.cbr.repository.CbrRepository
import tgd.company.educurrencyconverter.domain.mapper.DataToDomainModelMapper
import tgd.company.educurrencyconverter.domain.model.DomainModel
import tgd.company.educurrencyconverter.utils.setUpWorkDispatchers
import javax.inject.Inject


//Интерактор для использования во viewModels в presentation слое
class CbrCurrencyInteractorImpl @Inject constructor(
    private val cbrRepository: CbrRepository<DataModel>
) : CbrCurrencyInteractor<DomainModel> {

    private val mapper: DataToDomainModelMapper by lazy { DataToDomainModelMapper() }

    override fun clearLocalDb(): Flow<DomainModel> = cbrRepository.clear().map { mapper.call(it) }

    override fun updateLocalDb(): Flow<DomainModel> = flow {
        emit(DomainModel.Loading)
        cbrRepository.loadCurrencies().collect { dataModel ->
            when (dataModel) {
                is DataModel.Error -> emit(mapper.call(dataModel))
                is DataModel.Success<*> -> {
                    val domainModel = mapper.call(dataModel)
                    if (domainModel is DomainModel.Success<*>) {
                        if (domainModel.data is List<*>) {
                            if (domainModel.data.all { it is Currency }) {
                                cbrRepository.updateCurrencies(domainModel.data as List<Currency>)
                                emit(DomainModel.Success(domainModel.data))
                            }
                        }
                    }
                }
            }
        }
    }.setUpWorkDispatchers()

    override fun getAllCurrencies(): Flow<DomainModel> = cbrRepository.getCurrencies().map { mapper.call(it) }
}
