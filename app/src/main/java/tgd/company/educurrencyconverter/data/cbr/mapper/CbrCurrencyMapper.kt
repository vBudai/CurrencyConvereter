package tgd.company.educurrencyconverter.data.cbr.mapper

import tgd.company.educurrencyconverter.data.cbr.local.room.model.CurrencyRoom
import tgd.company.educurrencyconverter.data.cbr.model.CbrModel
import tgd.company.educurrencyconverter.data.cbr.remote.model.CurrenciesResponse
import tgd.company.educurrencyconverter.data.cbr.remote.model.CurrencyRemoteModel
import tgd.company.educurrencyconverter.data.cbr.repository.model.DataModel
import tgd.company.educurrencyconverter.data.exception.NotMappingDataModelObject
import tgd.company.educurrencyconverter.domain.cbr.model.Currency

class CbrCurrencyMapper {

    fun mapTo(cbrModel: CbrModel?): DataModel {
        return when (cbrModel?.javaClass?.name) {
            CurrenciesResponse::class.java.name -> {
                return DataModel.Success(
                    (cbrModel as CurrenciesResponse)
                        .valute.toString()
                        .toListCurrencyRemoteModel()
                        .map { it.toDomainModel() }
                )
            }
            CurrencyRoom::class.java.name -> DataModel.Success((cbrModel as CurrencyRoom).toDomainModel())
            else -> DataModel.Error(NotMappingDataModelObject())
        }
    }

    fun toRoom(currency: Currency): CurrencyRoom {
        return CurrencyRoom(
            charCode = currency.charCode,
            name = currency.name,
            value = currency.value,
            previous = currency.previous,
            nominal = currency.nominal
        )
    }

    fun mapListTo(list: List<CbrModel>?): DataModel {
        return when (list != null) {
            false -> DataModel.Error(NotMappingDataModelObject())
            true -> {
                DataModel.Success(
                    list.map { mapTo(it) }
                        .filterIsInstance<DataModel.Success<*>>()
                        .map { it.data }
                        .toList()
                )
            }
        }
    }

    fun mapListToRoom(list: List<Currency>): List<CurrencyRoom> = list.map(::toRoom)

    private fun String.toListCurrencyRemoteModel() : List<CurrencyRemoteModel> {
        return this.substring(1, this.length - 2)
            .split("}, ")
            .map { it.substring(5) }
            .map { it.toCurrencyRemoteModel()}
    }

    private fun String.toCurrencyRemoteModel(): CurrencyRemoteModel {

        val currencyRemoteModel = CurrencyRemoteModel()
        this.split(", ").map { field ->
            val result = field.split("=")
            when (result.first()) {
                "ID" -> currencyRemoteModel.id = result.last()
                "NumCode" -> currencyRemoteModel.numCode = result.last()
                "Nominal" -> currencyRemoteModel.nominal = result.last().toDouble()
                "CharCode" -> currencyRemoteModel.charCode = result.last()
                "Name" -> currencyRemoteModel.name = result.last()
                "Value" -> currencyRemoteModel.value = result.last().toDouble()
                "Previous" -> currencyRemoteModel.previous = result.last().toDouble()
            }
        }
        return currencyRemoteModel
    }

    companion object {

        fun CurrencyRoom.toDomainModel(): Currency = Currency(
            charCode = charCode,
            name = name,
            value = value,
            previous = previous,
            nominal = nominal
        )

        fun CurrencyRemoteModel.toDomainModel(): Currency = Currency(
            charCode = charCode,
            name = name,
            value = value,
            previous = previous,
            nominal = nominal
        )
    }
}