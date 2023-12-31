package com.synavos.androidassignment.di

import android.util.Log
import com.synavos.androidassignment.BuildConfig
import com.synavos.androidassignment.data.service.APIService
import com.synavos.androidassignment.network.adapter.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    fun provideRetrofit(
        @Named("BaseUrl") baseUrl: String,
        networkAdapter: ApiResponseCallAdapterFactory,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(networkAdapter).client(okHttpClient).baseUrl(baseUrl).build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor { message -> Log.d("okhttp", message) }
        return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).addInterceptor {
            val original = it.request()
            val newRequestBuilder = original.newBuilder()
            newRequestBuilder.addHeader("Content-Type", "application/json")
            newRequestBuilder.addHeader("Accept", "application/json")

            it.proceed(newRequestBuilder.build())
        }.addInterceptor {
            val original = it.request()
            val newUrl = original.url.newBuilder().build()
            val newRequest = original.newBuilder().url(newUrl).build()

            it.proceed(newRequest)
        }.addInterceptor(loggingInterceptor).callTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES).writeTimeout(1, TimeUnit.MINUTES).build()
    }


    @Provides
    fun provideAPIService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)

    @Singleton
    @Provides
    fun provideNetworkAdapter(): ApiResponseCallAdapterFactory {
        return ApiResponseCallAdapterFactory()
    }


}