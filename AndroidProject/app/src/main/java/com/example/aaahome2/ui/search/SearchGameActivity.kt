package com.example.aaahome2.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aaahome2.R
import com.example.aaahome2.Tools
import com.example.aaahome2.model.WishItem
import com.example.aaahome2.network.service.Network
import kotlinx.coroutines.*

class SearchGameActivity : AppCompatActivity() {
    lateinit var recycler:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_game)
        val edit = findViewById<EditText>(R.id.searchEdit)
        //设置recycleView适配器
        recycler=findViewById(R.id.recyclerSearch)
        recycler.layoutManager=LinearLayoutManager(this)
        edit.addTextChangedListener { text: Editable? ->
            val content=text.toString()
            GlobalScope.launch {
                val items=ArrayList<WishItem>()
                try{
                    withContext(Dispatchers.IO){
                        val game = Network.SearchByName(content)
                        for(i in game.ids){
                            items.add(Network.getGameById(i))
                        }
                    }
                    withContext(Dispatchers.Main){
                        refresh(items)
                    }
                }catch (e:Exception){
                    refresh(items)
                    Tools.showMes("没有结果！r")
                }
            }
        }
    }

    private fun refresh(items: ArrayList<WishItem>) {
        if(items.size==0) return
        val adapter=SearchAdapter(items)
        recycler.adapter=adapter

    }


}