package tgd.company.educurrencyconverter.data.cbr.repository.cbr

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import tgd.company.educurrencyconverter.data.cbr.local.room.CurrencyDao
import tgd.company.educurrencyconverter.data.cbr.mapper.CbrCurrencyMapper
import tgd.company.educurrencyconverter.data.cbr.remote.api.CbrApi
import tgd.company.educurrencyconverter.data.cbr.repository.model.DataModel
import tgd.company.educurrencyconverter.data.exception.NoSuccessfulLoadContentException
import tgd.company.educurrencyconverter.domain.cbr.model.Currency
import tgd.company.educurrencyconverter.domain.cbr.repository.CbrRepository
import javax.inject.Inject

class CbrRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit,
    private val currencyDao: CurrencyDao
) : CbrRepository<DataModel> {

    private val cbrApi: CbrApi by lazy { retrofit.create(CbrApi::class.java) }
    private val currencyMapper: CbrCurrencyMapper by lazy { CbrCurrencyMapper() }

    private fun <T> Flow<T>.setDefaultDispatchers() = this.flowOn(Dispatchers.IO)

    override fun loadCurrencies(): Flow<DataModel> = flow {
        emit(DataModel.Loading)
        val response = cbrApi.getCurrencies()
        when (response.isSuccessful) {
            false -> emit(DataModel.Error(NoSuccessfulLoadContentException()))
            true -> emit(currencyMapper.mapTo(response.body()))
        }
    }.setDefaultDispatchers()

    override fun getCurrencies(): Flow<DataModel> = flow {
        emit(DataModel.Loading)
        val result = currencyDao.getAll()
        when (result != null) {
            false -> emit(DataModel.Error(NoSuccessfulLoadContentException()))
            true -> emit(currencyMapper.mapListTo(result))
        }
    }.setDefaultDispatchers()

    override fun deleteCurrency(currency: Currency): Flow<DataModel> = flow {
        emit(DataModel.Loading)
        currencyDao.delete(currencyMapper.toRoom(currency))
        emit(DataModel.Success(true))

    }.setDefaultDispatchers()

    override fun clear(): Flow<DataModel> = flow {
        emit(DataModel.Loading)
        currencyDao.deleteAll()
        emit(DataModel.Success(true))
    }.setDefaultDispatchers()

    override fun updateCurrencies(currencies: List<Currency>): Flow<DataModel> = flow {
        emit(DataModel.Loading)
        currencyDao.insertAll(currencyMapper.mapListToRoom(currencies))
        emit(DataModel.Success(true))
    }.setDefaultDispatchers()
}