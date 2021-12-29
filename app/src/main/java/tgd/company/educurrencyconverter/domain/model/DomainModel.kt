package tgd.company.educurrencyconverter.domain.model

sealed class DomainModel {
    data class Error(val exception: Exception) : DomainModel()
    data class Success<T>(val data: T) : DomainModel()
    object Loading : DomainModel()
}