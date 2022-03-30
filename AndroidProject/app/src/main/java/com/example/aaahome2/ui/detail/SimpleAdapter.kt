package com.example.aaahome2.ui.detail

import android.content.ContentValues.TAG
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.aaahome2.AAAhomeApplication
import com.example.aaahome2.R
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder
import kotlinx.coroutines.*

class SimpleAdapter : BaseBannerAdapter<CustomBean>() {

    override fun bindData(
        holder: BaseViewHolder<CustomBean>,
        data: CustomBean?,
        position: Int,
        pageSize: Int
    ) {
        GlobalScope.launch {
            var drawable: Drawable?= Glide.with(AAAhomeApplication.context)
                .load(data!!.imageResUrl)
                .submit()
                .get()
            withContext(Dispatchers.Main){
                //这里是个更新ui的操作，需要放到主线程做
                holder.setImageDrawable(R.id.banner_image, drawable)
            }
        }
    }
    override fun getLayoutId(viewType: Int): Int {
        return R.layout.banner_item;
    }
}