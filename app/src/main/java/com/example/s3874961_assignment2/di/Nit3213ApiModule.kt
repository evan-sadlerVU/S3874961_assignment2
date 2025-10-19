// Direct injection module for the retrofit client to connnect to the API.
package com.example.s3874961_assignment2.di

import com.example.s3874961_assignment2.networking.Nit3213ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Nit3213ApiModule {
    private const val BASE_URL = "https://nit3213api.onrender.com/"

    /* Creates A HTTP Client, Singleton so is only created once.*/
    @Provides
    @Singleton
    fun provideOkHTTPClient(): OkHttpClient {
        // apply HTTP logging so we can see debugging info through logcat.
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    /* Provides Moshi, used to convert JSON to Kotlin objects.
    * Singleton so only created once */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    /* Provides the retrofit Object, used to create the API service (factory, kinda).
    * Singleton so only created once */
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    /* API service, the method of accessing retrofit elsewhere in the app.
    * Singleton so only created once */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): Nit3213ApiService {
        return retrofit.create(Nit3213ApiService::class.java)
    }
}