package tgd.company.educurrencyconverter.ui.model

sealed class UiModel {
    data class Error(val exception: Exception) : UiModel()
    data class Success<T>(val data: T) : UiModel()
    object Loading : UiModel()
    object FinishLoading : UiModel()
}