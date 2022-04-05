package com.example.aaahome2.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aaahome2.AAAhomeApplication
import com.example.aaahome2.AAAhomeApplication.Companion.context
import com.example.aaahome2.AAAhomeApplication.Companion.startActivity
import com.example.aaahome2.R
import com.example.aaahome2.model.Detail
import com.example.aaahome2.model.WishItem
import com.example.aaahome2.ui.detail.DetailActivity

class SearchAdapter(private val searchList:List<WishItem>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>(){
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val img: ImageView = view.findViewById(R.id.wish_img)
        val gameName: TextView = view.findViewById(R.id.game_name)
        val price: TextView = view.findViewById(R.id.price)
        val percent: TextView = view.findViewById(R.id.percent)
        val text3:TextView=view.findViewById(R.id.text3)
        val wishPrice: EditText =view.findViewById(R.id.wishPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.wishlist_item,parent,false)
        val res=ViewHolder(view)
        res.gameName.setOnClickListener {
            val position=res.adapterPosition
            val id=searchList[position].id
            startActivity<DetailActivity> {
                it.putExtra("id",id)
                it.putExtra("lowPriceTime",searchList[position].nearlyLowestTimestamp)
                it.putExtra("lowPrice",searchList[position].priceLowest)
                it.putExtra("relaseDate",searchList[position].relaseDate)
            }
        }
        res.img.setOnClickListener {
            val position=res.adapterPosition
            val id=searchList[position].id
            startActivity<DetailActivity> {
                it.putExtra("id",id)
                it.putExtra("lowPriceTime",searchList[position].nearlyLowestTimestamp)
                it.putExtra("lowPrice",searchList[position].priceLowest)
                it.putExtra("relaseDate",searchList[position].relaseDate)
            }
        }
        res.wishPrice.visibility=View.INVISIBLE
        res.text3.visibility=View.INVISIBLE
        return res
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchList[position]
        holder.gameName.text = item.gameName
        holder.price.text = "￥"+(item.priceNow/100).toString()
        var temp: Float = item.priceNow.toFloat() / item.mark1.toFloat()
        temp = (1.00F - temp) * 100
        holder.percent.text = "-" + temp.toInt().toString() + "%"
        if(item.mark1.toInt()==0)  holder.percent.text="-0%"
        Glide.with(context).load(item.imgSrc).fitCenter().into(holder.img)
    }

    override fun getItemCount(): Int =searchList.size

}