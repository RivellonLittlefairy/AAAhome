package com.example.aaahome2.network.service

import com.example.aaahome2.model.Detail
import com.example.aaahome2.model.SearchResult
import com.example.aaahome2.model.WishItem
import com.example.aaahome2.network.ServiceCreator
import retrofit2.await

object Network {
    //通过动态代理的方式拿到Service实例
    private val placeService = ServiceCreator.create(WishItemService::class.java)
    //通过id获取游戏简略信息
    suspend fun getGameById(id: Int): WishItem {
        return placeService.getWishItemById(id).await()
    }
    //通过id获取详细信息
    suspend fun getDetailById(id:Int):Detail{
        return placeService.getDetailById(id).await()
    }
    suspend fun SearchByName(name:String):SearchResult{
        return placeService.SearchByName(name).await()
    }
}


