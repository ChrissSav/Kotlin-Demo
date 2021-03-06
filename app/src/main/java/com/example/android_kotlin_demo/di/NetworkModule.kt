package com.example.android_kotlin_demo.di

import com.example.android_kotlin_demo.BuildConfig
import com.example.android_kotlin_demo.framework.NetworkConnectionInterceptor
import com.example.android_kotlin_demo.framework.UserService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson = GsonBuilder().create()


    @Singleton
    @Provides
    fun provideHttpLogger(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val apply = httpLoggingInterceptor.apply { httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS }
        return apply.apply { apply.level = HttpLoggingInterceptor.Level.BODY }
    }



    @Singleton
    @Provides
    fun provideNetworkConnectionInterceptor() = NetworkConnectionInterceptor()

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor,networkConnectionInterceptor : NetworkConnectionInterceptor): OkHttpClient.Builder {
        val client = OkHttpClient.Builder().proxy(Proxy.NO_PROXY)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(networkConnectionInterceptor)
        if (BuildConfig.DEBUG) {
            client.addInterceptor(loggingInterceptor)
        }
        return client
    }


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient.Builder): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient.build())
    }


    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit.Builder): UserService {
        return retrofit.baseUrl(BuildConfig.API_URL).build()
            .create(UserService::class.java)
    }
}