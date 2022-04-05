package com.example.aaahome2.ui.wishlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.aaahome2.AAAhomeApplication
import com.example.aaahome2.R
import com.example.aaahome2.Tools
import com.example.aaahome2.model.WishItem
import com.example.aaahome2.model.WishMap
import com.example.aaahome2.network.service.Network
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class WishViewModel: ViewModel() {
    val listData=MutableLiveData<ArrayList<WishItem>>()
    init {
        listData.value=ArrayList()
    }
    fun initList() {
        val res=ArrayList<WishItem>()
        val request: Request = Request.Builder()
            .addHeader("cookie", Tools.getToken())
            .url("http://${AAAhomeApplication.context.getString(R.string.basePort)}/getWishList")
            .build()
        GlobalScope.launch {
            withContext(Dispatchers.IO){
                val response = OkHttpClient().newCall(request).execute()
                val json= Tools.getJson(response)
                val map: WishMap = Gson().fromJson(json, WishMap::class.java)
                for (i in map.map.keys){
                    val gameById = Network.getGameById(i)
                    gameById.nearlyLowestTimestamp= map.map[i]!!
                    res.add(gameById)
                }
                if(res.size==0){
                    Tools.showMes("愿望单为空！")
                }
                listData.postValue(res)
            }
        }
    }
}