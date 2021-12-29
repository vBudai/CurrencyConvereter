package tgd.company.educurrencyconverter.data.other.interceptor

import android.content.Context
import android.content.res.AssetManager
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

class FakeCbrInterceptor @Inject constructor(private val context: Context) : Interceptor {

    private val fileName = "Cbr_response.json"

    override fun intercept(chain: Interceptor.Chain): Response {
        return when (chain.request().url.toString()) {
            "https://www.cbr-xml-daily.ru/daily_json.js" -> {
                buildResponse(context.assets.readAssetsFile(fileName), chain.request())
            }
            else -> chain.proceed(chain.request())
        }
    }

    private fun buildResponse(responseString: String, request: Request): Response {
        return Response.Builder()
            .code(200)
            .message(responseString)
            .request(request)
            .protocol(Protocol.HTTP_1_0)
            .body(responseString.toByteArray().toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("content-type", "application/json")
            .build()
    }

    private fun AssetManager.readAssetsFile(fileName: String): String {
        return open(fileName).bufferedReader().use { it.readText() }
    }
}