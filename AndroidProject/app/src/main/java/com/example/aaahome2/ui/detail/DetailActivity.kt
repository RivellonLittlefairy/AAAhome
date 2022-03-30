package com.example.aaahome2.ui.detail

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.example.aaahome2.AAAhomeApplication
import com.example.aaahome2.R
import com.example.aaahome2.Tools
import com.example.aaahome2.model.Detail
import com.example.aaahome2.network.service.Network
import com.example.aaahome2.ui.web.SteamWeb
import com.ms.square.android.expandabletextview.ExpandableTextView
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.constants.PageStyle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class DetailActivity : AppCompatActivity() {
    private lateinit var mViewPager: BannerViewPager<CustomBean>
    lateinit var bean:Detail
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        setContentView(R.layout.activity_detail)
        val id:Int=intent.getIntExtra("id",10)
        GlobalScope.launch {
            withContext(Dispatchers.Main){
                bean=Network.getDetailById(id)
                //为导航栏和基本信息设置游戏名
                findViewById<TextView>(R.id.detailGameNameHead).text = bean.gameName
                findViewById<TextView>(R.id.detailGameName).text = bean.gameName
                //设置轮播图
                setupViewPager()
                mViewPager.startLoop()
                val imgList = LinkedList<CustomBean>()
                for (url in bean.HDImg) {
                    imgList.add(CustomBean(url))
                }
                mViewPager.refreshData(imgList)
                //设置评价信息
                findViewById<TextView>(R.id.detailAppraise).text = bean.appraise
                //设置现价
                findViewById<TextView>(R.id.detailNowPrice).text =
                    "￥" + (bean.price.toDouble() / 100).toString()
                //设置原价
                val highPrice = findViewById<TextView>(R.id.detailHighPrice)
                //增加删除线，不懂
                //todo
                highPrice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
                highPrice.invalidate()
                findViewById<TextView>(R.id.detailHighPrice).text =
                    "￥" + (bean.highPrice.toDouble() / 100).toString()
                //可展开的文本框，用于放置游戏简介
                val expandText = findViewById<ExpandableTextView>(R.id.expand_text_view)
                expandText.text = bean.comments
                //设置加入愿望单按钮
                val addWishListButton = findViewById<Button>(R.id.addWishList)
                addWishListButton.setOnClickListener {
                    val id=bean.id
                    val map:Map<Int,Int> = mapOf(id to bean.highPrice/10)
                    Tools.addWishItem(map)
                }
                //设置跳转源页按钮
                val steamWeb = findViewById<Button>(R.id.steam_icon)
                steamWeb.setOnClickListener {
                    AAAhomeApplication.startActivity<SteamWeb> {
                        Log.d("mes", "put: ${bean.url}")
                        intent.putExtra("url", bean.url)
                    }
                }
            }
        }
    }

    private fun setupViewPager() {
        mViewPager = findViewById(R.id.bannerView)
        mViewPager.apply {
            adapter = SimpleAdapter()
            setLifecycleRegistry(lifecycle)
            setPageStyle(PageStyle.MULTI_PAGE_SCALE)
        }.create()
    }
}