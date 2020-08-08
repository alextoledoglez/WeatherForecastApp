package com.personal.weatherforecastapp.data.service

import com.personal.weatherforecastapp.data.utils.CacheManager
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {

    private const val BASE_URL: String = "https://api.openweathermap.org/data/2.5/"

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(provideCacheInterceptor())
        .cache(CacheManager.getCacheInstance())
        .build()

    private fun provideCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request: Request = chain.request()
            val cacheControl: CacheControl = CacheControl.Builder()
                .maxAge(CacheManager.MAX_AGE_VALUE, TimeUnit.MINUTES)
                .build()
            request
                .newBuilder()
                .removeHeader(CacheManager.HEADER_PRAGMA)
                .header(CacheManager.HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
            chain.proceed(request)
        }
    }

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val apiService: ApiService = initRetrofit()
        .create(ApiService::class.java)
}