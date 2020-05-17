package com.salesianostriana.damkeepapp.api

import android.content.Context
import android.content.SharedPreferences
import com.salesianostriana.damkeepapp.common.Constantes
import com.salesianostriana.damkeepapp.di.MyApp
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    val logging = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    @Named("url")
    fun provideBaseUrl(): String = Constantes.TMDBAPI_BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
        return with(OkHttpClient.Builder()) {
            addInterceptor(logging)
            addInterceptor(tokenInterceptor)
            connectTimeout(Constantes.TIMEOUT_INMILIS, TimeUnit.MILLISECONDS)
            build()
        }
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        val sharedPref = MyApp.instance?.getSharedPreferences(
            Constantes.SHARED_PREFS_FILE, Context.MODE_PRIVATE)
        return sharedPref
    }

    @Singleton
    @Provides
    fun provideNotaRetrofitService(@Named("url") baseUrl: String, okHttpClient: OkHttpClient): NotaService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(NotaService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRetrofitService(@Named("url") baseUrl: String, okHttpClient: OkHttpClient): UserService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(UserService::class.java)
    }
}