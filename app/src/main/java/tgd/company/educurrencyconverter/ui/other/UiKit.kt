package tgd.company.educurrencyconverter.ui.other

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tgd.company.educurrencyconverter.R

@Composable
fun BackgroundImage() {
    Image(
        painter = painterResource(id = R.drawable.background_image),
        contentDescription = "backgroundImage",
        modifier = Modifier
            .fillMaxSize(),
        Alignment.TopCenter
    )
}

@Composable
fun FlagImage(flag: String) {
    val imageName = when (flag) {
        "AMD" -> R.drawable.flag_amd
        "AUD" -> R.drawable.flag_aud
        "AZN" -> R.drawable.flag_azn
        "BGN" -> R.drawable.flag_bgn
        "BRL" -> R.drawable.flag_brl
        "BYN" -> R.drawable.flag_byn
        "CAD" -> R.drawable.flag_cad
        "CNY" -> R.drawable.flag_cny
        "MDL" -> R.drawable.flag_mdl
        "CZK" -> R.drawable.flag_czk
        "DKK" -> R.drawable.flag_dkk
        "EUR" -> R.drawable.flag_eur
        "GBP" -> R.drawable.flag_gbp
        "HKD" -> R.drawable.flag_hkd
        "HUF" -> R.drawable.flag_huf
        "INR" -> R.drawable.flag_inr
        "JPY" -> R.drawable.flag_jpy
        "KGS" -> R.drawable.flag_kgs
        "KRW" -> R.drawable.flag_krw
        "KZT" -> R.drawable.flag_kzt
        "NOK" -> R.drawable.flag_nok
        "PLN" -> R.drawable.flag_pln
        "RON" -> R.drawable.flag_ron
        "SEK" -> R.drawable.flag_sek
        "SGD" -> R.drawable.flag_sgd
        "TRY" -> R.drawable.flag_try
        "UAH" -> R.drawable.flag_uah
        "USD" -> R.drawable.flag_usd
        "UZS" -> R.drawable.flag_uzs
        "ZAR" -> R.drawable.flag_zar
        "CHF" -> R.drawable.flag_chf
        "RUS" -> R.drawable.flag_rus
        else -> {
            R.drawable.ic_baseline_flag_24
        }
    }

    Image(
        painterResource(id = imageName), contentDescription = "flag", modifier = Modifier.size(60.dp, 40.dp)
    )
}

//Ячейка валюты для списка
@Composable
fun Valute(valute: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            FlagImage(flag = valute)
            TextValute("100.0")
        }
    }
}

@Composable
fun TextValute(text: String) {
    Text(text = text, modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth(), fontWeight = FontWeight.Bold)
}

@Composable
fun HideIcon(
    color: Color = Color.White
) = Icon(
    painterResource(
        id = R.drawable.ic_baseline_expand_more_24
    ),
    contentDescription = "hide",
    tint = color,
    modifier = Modifier.size(27.dp),
)

@Composable
fun DeleteIcon() = Icon(
    painterResource(
        id = R.drawable.ic_baseline_chevron_left_24
    ),
    contentDescription = "delete",
    tint = Color.White,
    modifier = Modifier.size(27.dp)
)

@Composable
fun ClearIcon() = Icon(
    painterResource(
        id = R.drawable.ic_baseline_backspace_24
    ),
    contentDescription = "clear",
    tint = Color.White,
    modifier = Modifier.size(27.dp)
)

@Composable
fun ResetIcon() = Icon(
    painterResource(id = R.drawable.ic_reset_svgrepo_com),
    contentDescription = "reset",
    tint = Color.White,
    modifier = Modifier.size(27.dp)
)

@Composable
fun CustomIconButton(
    icon: @Composable () -> Unit,
    event: () -> Unit = {}
) {
    IconButton(
        onClick = { event() }, modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        icon.invoke()
    }
}

@Composable
fun CustomTextButton(
    text: @Composable () -> Unit = { Text(text = "") },
    event: () -> Unit = {}
) {
    TextButton(
        onClick = { event() },
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
    ) {
        text.invoke()
    }
}

@Composable
fun TextStyle_Black_24_bold(
    text: String
) {
    Text(text = text, color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun TextStyle_Black_24(
    text: String
) {
    Text(text = text, color = Color.Black, fontSize = 24.sp)
}
