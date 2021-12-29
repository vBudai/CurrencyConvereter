package tgd.company.educurrencyconverter.data.cbr.repository.model

sealed class DataModel {
    data class Error(val exception: Exception) : DataModel()
    data class Success<T>(val data: T) : DataModel()
    object Loading : DataModel()
}