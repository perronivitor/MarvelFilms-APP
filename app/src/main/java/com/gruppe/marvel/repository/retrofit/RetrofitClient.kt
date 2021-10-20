package com.gruppe.unifique.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private lateinit var retrofit: Retrofit
    private const val BASE_URL="https://private-b34167-rvmarvel.apiary-mock.com/"

    //Função de construção Retrofit
    private fun getRetrofitInstance() :Retrofit{
        val httpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
        if (!::retrofit.isInitialized){
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }
        return retrofit
    }

    //Instancia do Retrofit
    val service: Service by lazy {
        getRetrofitInstance().create(Service::class.java)
    }


}