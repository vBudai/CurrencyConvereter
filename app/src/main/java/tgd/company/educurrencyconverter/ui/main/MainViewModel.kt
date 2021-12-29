package tgd.company.educurrencyconverter.ui.main

import android.util.Log
import androidx.compose.ui.tooling.preview.UiMode
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tgd.company.educurrencyconverter.data.other.interceptor.FakeCbrInterceptor
import tgd.company.educurrencyconverter.domain.cbr.interactor.CbrCurrencyInteractor
import tgd.company.educurrencyconverter.domain.cbr.model.Currency
import tgd.company.educurrencyconverter.domain.model.DomainModel
import tgd.company.educurrencyconverter.ui.model.UiModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cbrInterceptor: CbrCurrencyInteractor<DomainModel>
) : ViewModel() {

    companion object {
        val MAIN_CURRENCY = Currency(
            "RUS",
            "Российский рубль",
            1.0,
            1.0,
            1.0
        )
        const val MAX_LENGTH = 21
    }

    private val _currencyItems: MutableStateFlow<UiModel> = MutableStateFlow(UiModel.Loading)
    val currencyItem: StateFlow<UiModel> = _currencyItems

    private val _loaderState: MutableStateFlow<UiModel> = MutableStateFlow(UiModel.Loading)
    val loaderState: StateFlow<UiModel> = _loaderState

    private val _ruMainCurrency: MutableStateFlow<String> = MutableStateFlow("0")
    val ruMainCurrency: StateFlow<String> = _ruMainCurrency

    init {
        updateList()
    }

    fun initial() = viewModelScope.launch {
        cbrInterceptor.getAllCurrencies().collect { domainModel ->
            when(domainModel) {
                DomainModel.Loading -> _currencyItems.value = UiModel.Loading
                is DomainModel.Error -> _currencyItems.value = UiModel.Error(domainModel.exception)
                is DomainModel.Success<*> -> (domainModel.data as List<*>).also { _currencyItems.value =
                    UiModel.Success(it as List<Currency>)
                }
            }
        }
    }

    fun updateList() = viewModelScope.launch {
        cbrInterceptor.updateLocalDb().collect { domainModel ->
            when(domainModel) {
                DomainModel.Loading -> _loaderState.value = UiModel.Loading
                is DomainModel.Error -> _loaderState.value = UiModel.Error(domainModel.exception)
                is DomainModel.Success<*> -> (domainModel.data as List<*>).also { _currencyItems.value =
                    UiModel.Success(it as List<Currency>)
                }
            }
        }
    }

    fun calculateEvent(event: CalculateEvent) {
        when(event) {
            is CalculateEvent.ADD ->  {
                if (_ruMainCurrency.value.length <= MAX_LENGTH) {
                    if (!(event.value == 0 && _ruMainCurrency.value == "0")) {
                        if (_ruMainCurrency.value == "0") {
                            _ruMainCurrency.value = event.value.toString()
                        } else {
                            _ruMainCurrency.value = _ruMainCurrency.value + event.value
                        }
                    }
                }
            }
            CalculateEvent.CLEAR -> {
                when(_ruMainCurrency.value.length) {
                    1 -> { _ruMainCurrency.value = "0" }
                    else -> _ruMainCurrency.value = _ruMainCurrency.value.dropLast(1)
                }
            }
            CalculateEvent.DOT -> {
                if (!_ruMainCurrency.value.contains(".") && _ruMainCurrency.value.length <= MAX_LENGTH) {
                    _ruMainCurrency.value = _ruMainCurrency.value + "."
                }
            }
            CalculateEvent.RESET -> _ruMainCurrency.value = "0"
            else -> {}
        }
    }
}