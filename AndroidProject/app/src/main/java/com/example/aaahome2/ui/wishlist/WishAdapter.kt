package com.example.aaahome2.ui.wishlist

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aaahome2.AAAhomeApplication
import com.example.aaahome2.R
import com.example.aaahome2.Tools
import com.example.aaahome2.Tools.Companion.changeGame
import com.example.aaahome2.Tools.Companion.removeGame
import com.example.aaahome2.Tools.Companion.showMes
import com.example.aaahome2.model.WishItem
import com.example.aaahome2.ui.library.LibraryItemAdapter
import kotlinx.coroutines.newFixedThreadPoolContext

class WishItemAdapter(val context: Context, private val wishList: List<WishItem>) :
    RecyclerView.Adapter<WishItemAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.wish_img)
        val gameName: TextView = view.findViewById(R.id.game_name)
        val price: TextView = view.findViewById(R.id.price)
        val percent: TextView = view.findViewById(R.id.percent)
        val wishPrice:EditText=view.findViewById(R.id.wishPrice)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WishItemAdapter.ViewHolder {
        AAAhomeApplication.mes="长按封面移除愿望单！"
        val view = LayoutInflater.from(context).inflate(R.layout.wishlist_item, parent, false)
        var viewHolder=ViewHolder(view)
        viewHolder.img.setOnLongClickListener {
            val position=viewHolder.adapterPosition
            removeGame(wishList[position].id)
            false
        }
        viewHolder.wishPrice.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND
                || actionId == EditorInfo.IME_ACTION_DONE
                || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())
            ) {
                //处理事件
                val position=viewHolder.adapterPosition
                changeGame(wishList[position].id, v.text.toString().toInt()*100)
                showMes(v.text.toString()+"刷新页面以保存！")
                v.clearFocus()
            }
            false;
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: WishItemAdapter.ViewHolder, position: Int) {
        val item = wishList[position]
        holder.gameName.text = item.gameName
        holder.price.text = "￥"+(item.priceNow/100).toString()
        var temp: Float = item.priceNow.toFloat() / item.mark1.toFloat()
        temp = (1.00F - temp) * 100
        holder.percent.text = "-" + temp.toInt().toString() + "%"
        if(item.mark1.toInt()==0)  holder.percent.text="-0%"
        Glide.with(context).load(item.imgSrc).fitCenter().into(holder.img)
        holder.wishPrice.hint=(wishList[position].nearlyLowestTimestamp/100).toString()
    }

    override fun getItemCount(): Int {
        return wishList.size
    }

}