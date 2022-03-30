package com.example.aaahome2.network.service

import com.example.aaahome2.model.Detail
import com.example.aaahome2.model.WishItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WishItemService {
    @GET("getById/{id}")
    fun getWishItemById(@Path("id") id: Int): Call<WishItem>
    @GET("getDetailById/{id}")
    fun getDetailById(@Path("id")id: Int):Call<Detail>
}