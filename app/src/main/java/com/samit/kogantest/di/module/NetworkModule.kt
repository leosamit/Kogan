package com.samit.kogantest.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.samit.kogantest.api.ApiKeys.Companion.ENDPOINT
import com.samit.kogantest.api.KoganService
import com.samit.kogantest.data.pagination.ProductRemoteDataSource
import com.samit.kogantest.di.qualifier.CoroutineScopeIO
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(ENDPOINT)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            //  .cache(cache)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addNetworkInterceptor(interceptor)
            .addNetworkInterceptor(StethoInterceptor())
        return client.build()
    }


    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): KoganService {
        return retrofit.create(KoganService::class.java)
    }

    @Singleton
    @Provides
    fun provideProductRemoteDataSource(service: KoganService) =
        ProductRemoteDataSource(service)

    @CoroutineScopeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.Main)
}