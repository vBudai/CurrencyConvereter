package tgd.company.educurrencyconverter.data.cbr.remote.model

import com.google.gson.annotations.SerializedName
import tgd.company.educurrencyconverter.data.cbr.model.CbrModel
import java.io.Serializable

data class CurrencyRemoteModel(
    @SerializedName("ID") var id : String = "",
    @SerializedName("NumCode") var numCode : String = "",
    @SerializedName("Nominal") var nominal : Double = 0.0,
    @SerializedName("CharCode") var charCode : String = "",
    @SerializedName("Name") var name : String = "",
    @SerializedName("Value") var value : Double = 0.0,
    @SerializedName("Previous") var previous : Double = 0.0
): CbrModel(), Serializable