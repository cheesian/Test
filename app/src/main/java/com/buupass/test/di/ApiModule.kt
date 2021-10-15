package com.buupass.test.di

import com.buupass.test.BuildConfig
import com.buupass.test.api.GetUserApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val API_URL = "https://reqres.in"
@Module
class ApiModule {

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {

        return with(HttpLoggingInterceptor()) {
            if (BuildConfig.DEBUG)
                level = HttpLoggingInterceptor.Level.BODY
            else
                level = HttpLoggingInterceptor.Level.BASIC
            this
        }

    }

    @Provides
    fun provideOKHTTPClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }

    @Provides
    fun provideUserApiService(retrofit: Retrofit): GetUserApiService {
        return retrofit.create(GetUserApiService::class.java)
    }

}