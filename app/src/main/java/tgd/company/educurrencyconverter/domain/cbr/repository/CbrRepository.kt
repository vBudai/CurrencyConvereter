package tgd.company.educurrencyconverter.domain.cbr.repository

import kotlinx.coroutines.flow.Flow
import tgd.company.educurrencyconverter.domain.cbr.model.Currency

interface CbrRepository<DataModel> {


    //Загрузка списка валют с сервера CBR
    fun loadCurrencies(): Flow<DataModel>


    //Сохранение и обновление данных в локальной бд
    fun updateCurrencies(currencies: List<Currency>): Flow<DataModel>


    //Получение списка валют из локальной бд
    fun getCurrencies(): Flow<DataModel>


    //Удаление конкретной валюты из локальной бд
    fun deleteCurrency(currency: Currency): Flow<DataModel>


    //Полная очистка таблицы от всех данных
    fun clear(): Flow<DataModel>
}