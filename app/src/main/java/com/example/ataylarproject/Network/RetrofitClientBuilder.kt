package com.example.ataylarproject.Network

import com.example.ataylarproject.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClientBuilder {

    private var retrofit : Retrofit? = null
    private const val BASE_API_URL = "http://10.0.2.2:8081/customer-api/"
    var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    val client: Retrofit?
    get() {

        if (retrofit == null) {
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(accessTokenProvidingInterceptor())
                .addInterceptor(headersInterceptor())
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build() //bitirmek için
               // .create(ApiInterface::class.java)

        }

        return retrofit
    }

    private fun accessTokenProvidingInterceptor() = Interceptor { chain ->
        if ("" != Constants.AUTH_TOKEN)
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader("X-Auth-Token" , Constants.AUTH_TOKEN)
                    .build()
            )
        else
            chain.proceed(chain.request())
    }

    private fun headersInterceptor() = Interceptor {
        chain ->
        chain.proceed(chain.request().newBuilder()
            .addHeader("Accept", "application/json; charset = UTF-8")
            .addHeader("Content-Type", "application/json; charset = UTF-8")
            .build()
        )
    }
}