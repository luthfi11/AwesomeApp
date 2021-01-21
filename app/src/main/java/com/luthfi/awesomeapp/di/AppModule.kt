package com.luthfi.awesomeapp.di

import com.luthfi.awesomeapp.core.api.ApiService
import com.luthfi.awesomeapp.core.repository.ImageRepository
import com.luthfi.awesomeapp.ui.main.MainViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val cacheSize = 10 * 1024 * 1024

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .cache(Cache(androidContext().cacheDir, cacheSize.toLong()))
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(8, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.pexels.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    factory { ImageRepository(get()) }
}
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}