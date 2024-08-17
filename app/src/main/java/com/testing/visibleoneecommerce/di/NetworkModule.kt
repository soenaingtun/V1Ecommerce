package com.testing.visibleoneecommerce.di

import com.testing.visibleoneecommerce.network.ProductApiService
import com.testing.visibleoneecommerce.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Provides
//    @Singleton
//    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(Constants.ROUTE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }


    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
        okHttpClientBuilder.connectTimeout(100, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(100, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(100, TimeUnit.SECONDS)
        return okHttpClientBuilder.build()
    }


    @Provides
    @Singleton
    fun provideProductServiceApi(okHttpClient: OkHttpClient): ProductApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.ROUTE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApiService::class.java)
    }
}