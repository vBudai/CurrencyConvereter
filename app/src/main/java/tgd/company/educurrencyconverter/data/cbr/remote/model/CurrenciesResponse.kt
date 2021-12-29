package tgd.company.educurrencyconverter.data.cbr.remote.model

import com.google.gson.annotations.SerializedName
import tgd.company.educurrencyconverter.data.cbr.model.CbrModel

data class CurrenciesResponse (
	@SerializedName("Date") val date : String,
	@SerializedName("PreviousDate") val previousDate : String,
	@SerializedName("PreviousURL") val previousURL : String,
	@SerializedName("Timestamp") val timestamp : String,
	@SerializedName("Valute") val valute : Any
) : CbrModel()