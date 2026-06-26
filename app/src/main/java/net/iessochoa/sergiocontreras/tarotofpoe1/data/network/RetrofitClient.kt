package net.iessochoa.sergiocontreras.tarotofpoe1.data.network

import android.content.Context
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitClient {

    //Ponemos la base URL al worker de Cloudflare que hemos creado
    private const val BASE_URL = "https://tarotofpoe1.contry-1990.workers.dev/"

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Volatile
    private var apiService: TarotOfPoeApiService? = null

    fun getApiService(context: Context): TarotOfPoeApiService {
        return apiService ?: synchronized(this) {
            apiService ?: build(context.applicationContext).also { apiService = it }
        }
    }

    private fun build(context: Context): TarotOfPoeApiService {
        val okHttp = OkHttpClient.Builder()
            //.addInterceptor(MockInterceptor(context))
            .build()

        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(TarotOfPoeApiService::class.java)
    }
}
