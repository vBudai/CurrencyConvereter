package tgd.company.educurrencyconverter.domain.cbr.interactor

import kotlinx.coroutines.flow.Flow

interface CbrCurrencyInteractor<DomainModel> {

    //Полностью очищает локальную бд
    fun clearLocalDb(): Flow<DomainModel>


    //Выгружает список валют с сервера ЦБ и обновляет данные в бд
    fun updateLocalDb(): Flow<DomainModel>


    //Возвращает список всеъ валют из локальной бд
    fun getAllCurrencies(): Flow<DomainModel>
}