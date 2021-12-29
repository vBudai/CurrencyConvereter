package tgd.company.educurrencyconverter.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tgd.company.educurrencyconverter.domain.cbr.model.Currency
import tgd.company.educurrencyconverter.ui.model.UiModel
import tgd.company.educurrencyconverter.ui.other.BackgroundImage
import tgd.company.educurrencyconverter.ui.other.FlagImage
import tgd.company.educurrencyconverter.ui.other.TextValute
import tgd.company.educurrencyconverter.ui.theme.EDUCurrencyConverterTheme

@Composable
fun MainView(viewModel: MainViewModel) {

    val items by viewModel.currencyItem.collectAsState()
    val mainValue by viewModel.ruMainCurrency.collectAsState()

    MainScreen(
        currencyItems = items,
        calculateEvent = { viewModel.calculateEvent(it) },
        mainValue = mainValue
    )
}

@Composable
fun MainScreen(
    currencyItems: UiModel,
    calculateEvent: (CalculateEvent) -> Unit,
    mainValue: String
) {
    //Фоновая картинка
    BackgroundImage()

    //Весь экран
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .height(0.dp),
        ) {
//            //Валюты
            ValuteList(currencyItems, mainValue)
        }

        MainCurrency(
            currency = MainViewModel.MAIN_CURRENCY,
            mainValue
        )

        CalculatorTable(calculateEvent)
    }
}

@Composable
fun MainCurrency(currency: Currency, value: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            FlagImage(flag = currency.charCode)
            TextValute(value)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, name = "DefaultPreviewMainScreen")
@Composable
fun DefaultPreviewMainScreen() {

    val prevItems = UiModel.Success(
        data = listOf(
            Currency("EUR", "Евро", 81.6552, 81.787, 1.0),
            Currency("KZT", "Казахстанских тенге", 16.5669, 16.4867, 100.0)
        )
    )

    EDUCurrencyConverterTheme {
        MainScreen(
            currencyItems = prevItems,
            calculateEvent = {},
            mainValue = "123.123"
        )
    }
}