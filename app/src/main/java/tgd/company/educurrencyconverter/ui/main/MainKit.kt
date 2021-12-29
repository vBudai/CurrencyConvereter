package tgd.company.educurrencyconverter.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import tgd.company.educurrencyconverter.domain.cbr.model.Currency
import tgd.company.educurrencyconverter.ui.model.UiModel
import tgd.company.educurrencyconverter.ui.other.*
import tgd.company.educurrencyconverter.utils.currencyMath

@Composable
fun ValuteMain(valute: String){
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .padding(10.dp, 0.dp)){
        Row(horizontalArrangement = Arrangement.Start) {
            FlagImage(flag = valute)
            TextValute(valute)
        }
        Text(text="")
    }
}

//Список валют
@Composable
fun ValuteList(currencyItems: UiModel, mainValue: String) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 10.dp, 10.dp, 0.dp),
        shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp),
        backgroundColor = Color(0xFFF5F6F8)
    ) {
        when(currencyItems) {
            is UiModel.Success<*> -> {
                LazyColumn(){
                    items(currencyItems.data as List<Currency>) { item: Currency ->
                        Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                            FlagImage(flag = item.charCode)
                            TextValute(currencyMath(item, mainValue))
                        }
                    }
                }
            }
            else -> {}
        }
    }
}

//Цифры калькулятора
@Composable
fun CalculatorTable(calculateEvent: (CalculateEvent) -> Unit) {
    Surface(
        color = Color(0xFFF9E0E4),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
            , horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.25f)) {
                CustomTextButton(
                    text = { TextStyle_Black_24(text = "1") },
                    event = { calculateEvent.invoke(CalculateEvent.ADD(1)) }
                )
                CustomTextButton(
                    text = { TextStyle_Black_24(text = "4") },
                    event = { calculateEvent.invoke(CalculateEvent.ADD(4)) }
                )
                CustomTextButton(
                    text = { TextStyle_Black_24(text = "7") },
                    event = { calculateEvent.invoke(CalculateEvent.ADD(7)) }
                )
                CustomTextButton(
                    text = { TextStyle_Black_24(text = ".") },
                    event = { calculateEvent.invoke(CalculateEvent.DOT) }
                )
            }
            Column(modifier = Modifier.fillMaxWidth(0.33f)) {
                CustomTextButton(
                    text = { TextStyle_Black_24(text = "2") },
                    event = { calculateEvent.invoke(CalculateEvent.ADD(2)) }
                )
                CustomTextButton(
                    text = { TextStyle_Black_24(text = "5") },
                    event = { calculateEvent.invoke(CalculateEvent.ADD(5)) }
                )
                CustomTextButton(
                    text = { TextStyle_Black_24(text = "8") },
                    event = { calculateEvent.invoke(CalculateEvent.ADD(8)) }
                )
                CustomTextButton(
                    text = { TextStyle_Black_24(text = "0") },
                    event = { calculateEvent.invoke(CalculateEvent.ADD(0)) }
                )
            }
            Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                CustomTextButton(
                    text = { TextStyle_Black_24(text = "3") },
                    event = { calculateEvent.invoke(CalculateEvent.ADD(3)) }
                )
                CustomTextButton(
                    text = { TextStyle_Black_24(text = "6") },
                    event = { calculateEvent.invoke(CalculateEvent.ADD(6)) }
                )
                CustomTextButton(
                    text = { TextStyle_Black_24(text = "9") },
                    event = { calculateEvent.invoke(CalculateEvent.ADD(9)) }
                )
                CustomIconButton(icon = { HideIcon(Color.Black) })
            }
            Surface(color = Color(0xFF377BB2), modifier = Modifier.fillMaxWidth(1f)) {
                Column() {

//                    CustomIconButton(icon = { DeleteIcon() }) { calculateEvent.invoke(CalculateEvent.DELETE) }
                    CustomIconButton(icon = { ClearIcon() }) { calculateEvent.invoke(CalculateEvent.CLEAR) }
                    CustomIconButton(icon = { ResetIcon() }) { calculateEvent.invoke(CalculateEvent.RESET) }
                    CustomTextButton()
                    CustomTextButton()
                }
            }
        }
    }
}