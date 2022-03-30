package com.example.aaahome2.ui.library

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aaahome2.AAAhomeApplication
import com.example.aaahome2.R
import com.example.aaahome2.Tools.Companion.addWishItem
import com.example.aaahome2.model.Detail
import com.example.aaahome2.model.WishItem
import com.example.aaahome2.ui.detail.DetailActivity
import java.util.concurrent.TimeoutException

class LibraryItemAdapter(val context: Context, val wishList: List<WishItem>) :
    RecyclerView.Adapter<LibraryItemAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id:Int=11
        val img: ImageView = view.findViewById(R.id.libr_img)
        val gameName: TextView = view.findViewById(R.id.game_name)
        val appraise: TextView = view.findViewById(R.id.appraise)
        val price: TextView = view.findViewById(R.id.price)
        val percent: TextView = view.findViewById(R.id.percent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.library_item, parent, false)
        var viewHolder=ViewHolder(view)
        viewHolder.img.setOnClickListener {
            val position=viewHolder.adapterPosition
            val id=wishList[position].id
            AAAhomeApplication.startActivity<DetailActivity> {
                it.putExtra("id",id)
            }
        }
        viewHolder.img.setOnLongClickListener {
            val position=viewHolder.adapterPosition
            if (wishList[position].priceNow==0){
                Toast.makeText(
                    AAAhomeApplication.context, "已经免费啦，快开玩把！", Toast.LENGTH_SHORT
                ).show()
            }else{
                val id=wishList[position].id
                val map:Map<Int,Int> = mapOf(id to wishList[position].mark1.toInt()/10)
                addWishItem(map)
            }
            true
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = wishList[position]
        holder.id=item.id
        holder.appraise.text = item.appraise
        holder.gameName.text = item.gameName
        holder.price.text = "￥"+(item.priceNow/100).toString()
        var temp: Float = item.priceNow.toFloat() / item.mark1.toFloat()
        temp = (1.00F - temp) * 100
        holder.percent.text = "-" + temp.toInt().toString() + "%"
        if(item.mark1.toInt()==0)  holder.percent.text="-0%"
        Glide.with(context).load(item.imgSrc).fitCenter().into(holder.img)
        if (item.appraise == "好评如潮") holder.appraise.setTextColor(Color.parseColor("#E10808"))
        if (item.appraise == "特别好评") holder.appraise.setTextColor(Color.parseColor("#036805"))
    }

    override fun getItemCount(): Int = wishList.size
}
