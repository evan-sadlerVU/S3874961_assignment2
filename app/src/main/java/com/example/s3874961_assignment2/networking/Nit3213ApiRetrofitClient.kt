/*-- Retrofit Client to Communicate with the Nit3213 API --*/
package com.example.s3874961_assignment2.networking

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Nit3213ApiRetrofitClient {
    // The Base URL for the NIT3213 api provider
    private val BASE_URL = "https://nit3213api.onrender.com/" // The API provider address
    // Setup HTTP logging
    private val httpLogger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(httpLogger)
        .build()
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()
    val nit3213ApiService: Nit3213ApiService = retrofit.create(Nit3213ApiService::class.java)
}