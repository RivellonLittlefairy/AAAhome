package com.example.aaahome2.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL="http://124.223.63.146:8080/"

    private val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun<T> create(serviceClass: Class<T>):T= retrofit.create(serviceClass)

    //inline fun <reified T> create():T= create(T::class.java)
}