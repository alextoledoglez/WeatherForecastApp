package com.personal.weatherforecastapp.di

import android.content.Context
import com.personal.weatherforecastapp.data.remote.ApiService
import com.personal.weatherforecastapp.data.remote.RemoteDataSource
import com.personal.weatherforecastapp.data.repository.ForecastDataRepository
import com.personal.weatherforecastapp.domain.repository.ForecastRepository
import com.personal.weatherforecastapp.ui.viewModels.ForecastViewModel
import okhttp3.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun Scope.getRetrofit() = get<Retrofit>()

object MainModule {

    var CACHE_MAX_AGE_VALUE = 5
    var CACHE_MAX_STALE_VALUE = 1
    var HEADER_PRAGMA = "Pragma"
    var HEADER_CACHE_CONTROL = "Cache-Control"
    private const val CACHE_SIZE = (5 * 1024 * 1024).toLong()

    private val networkModule = module {

        fun provideUrlBase(): String = "https://api.openweathermap.org/data/2.5/"

        fun provideCache(context: Context) = Cache(context.cacheDir, CACHE_SIZE)

        fun provideCacheInterceptor() = Interceptor { chain ->
            val request: Request = chain.request()
            val cacheControl: CacheControl = CacheControl.Builder()
                .maxAge(CACHE_MAX_AGE_VALUE, TimeUnit.MINUTES)
                .build()
            request
                .newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
            chain.proceed(request)
        }

        fun provideOkHttpClient(cache: Cache) = OkHttpClient.Builder()
            .addNetworkInterceptor(provideCacheInterceptor())
            .cache(cache)
            .build()

        fun provideRetrofit(urlBase: String, okHttpClient: OkHttpClient) = Retrofit.Builder()
            .baseUrl(urlBase)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        single { provideUrlBase() }
        single { provideCache(get()) }
        single { provideOkHttpClient(get()) }
        single { provideRetrofit(get(), get()) }
        single { getRetrofit().create(ApiService::class.java) }
    }

    private val dataModule = module {
        single { RemoteDataSource(get()) }
        single<ForecastRepository> { ForecastDataRepository(get()) }
    }

    private val viewModelsModule = module {
        viewModel { ForecastViewModel(get()) }
    }

    fun initialize() = loadKoinModules(listOf(networkModule, dataModule, viewModelsModule))
}