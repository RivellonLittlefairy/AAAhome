package com.example.aaahome2.ui.search

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
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
        //将状态栏图标变成深色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(this.resources.getColor(android.R.color.white)) //设置状态栏颜色
            getWindow().decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //状态栏为白色 图标显示深色
        }
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_game)
        findViewById<TextView>(R.id.search_cancel).setOnClickListener {
            finish()
        }
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
                    withContext(Dispatchers.Main){
                        refresh(items)
                    }
                    Tools.showMes("没有结果！")
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