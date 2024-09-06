package com.lokal.cryptocurrencyexchangerates.di


import android.app.Application
import android.content.Context
import com.itssuryansh.mvvmapplication.remote.NewsAPI
import com.itssuryansh.mvvmapplication.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


// using the hilt to make the application of the production level
// this is NetworkModule from here all Retrofit and network related work has to be done

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = Constant.URL

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): NewsAPI {
        return retrofit.create(NewsAPI::class.java)
    }
}
