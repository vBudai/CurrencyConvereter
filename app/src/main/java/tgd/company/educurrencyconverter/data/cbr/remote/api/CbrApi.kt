package tgd.company.educurrencyconverter.data.cbr.remote.api

import retrofit2.Response
import retrofit2.http.GET
import tgd.company.educurrencyconverter.data.cbr.remote.model.CurrenciesResponse

interface CbrApi {

    @GET("/daily_json.js")
    suspend fun getCurrencies(): Response<CurrenciesResponse>

}
