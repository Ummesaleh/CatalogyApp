package com.states.catalogyapp.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//api client that connects  with our base url
object RetrofitInstance {
    private const val BASE_URL = "https://dummyjson.com/" // api url

    private val retrofit by lazy { // Lazy initialization
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                         // here we log in our api requests/responses
                    })
                    .build()
            )
            .build()
    }

    val api: ProductApi by lazy {
        retrofit.create(ProductApi::class.java) //  api service create
    }
}