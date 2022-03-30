package com.example.aaahome2

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.aaahome2.model.WishMap
import com.example.aaahome2.ui.login.LoginActivity
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class Tools {
    companion object {
        fun addWishItem(map: Map<Int, Int>) {
            val json = Gson().toJson(WishMap(map))
            val request = Request.Builder()
                .addHeader("cookie", getToken())
                .addHeader("map", json)
                .url("http://${AAAhomeApplication.context.getString(R.string.basePort)}/saveWishById")
                .build()
            GlobalScope.launch {
                val response = OkHttpClient().newCall(request).execute()
                if (response.header("status").equals("timeout")) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            AAAhomeApplication.context, "timeout", Toast.LENGTH_SHORT
                        ).show()
                        //将过期token去除
                        val edit =
                            AAAhomeApplication.context.getSharedPreferences("token", 0).edit()
                        edit.putString("token", "")
                        edit.apply()
                        AAAhomeApplication.startActivity<LoginActivity>()
                        false
                    }
                }
                showMes("成功添加到愿望单！")
            }
        }

        fun getToken(): String {
            val edit =
                AAAhomeApplication.context.getSharedPreferences("token", 0)
            val token = edit.getString("token", "")
            return "token=$token"
        }
        fun getJson(response:Response):String{
            val bytes = response.body()?.bytes()
            if (bytes!=null) return String(bytes)
            return ""
        }
        fun showMes(mes:String){
            GlobalScope.launch {
                withContext(Dispatchers.Main){
                    Toast.makeText(
                        AAAhomeApplication.context, mes, Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        fun removeGame(gid:Int){
            GlobalScope.launch {
                withContext(Dispatchers.IO){
                    val request:Request=Request.Builder()
                        .addHeader("cookie", getToken())
                        .addHeader("gid",gid.toString())
                        .url("http://${AAAhomeApplication.context.getString(R.string.basePort)}/removeWishById")
                        .build()
                    val response=OkHttpClient().newCall(request).execute()
                    if (response.header("status").equals("timeout")) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                AAAhomeApplication.context, "timeout", Toast.LENGTH_SHORT
                            ).show()
                            //将过期token去除
                            val edit =
                                AAAhomeApplication.context.getSharedPreferences("token", 0).edit()
                            edit.putString("token", "")
                            edit.apply()
                            AAAhomeApplication.startActivity<LoginActivity>()
                            false
                        }
                    }
                    showMes("成功删除")

                }
            }
        }
        fun changeGame(gid:Int,wishPrice:Int){
            GlobalScope.launch {
                withContext(Dispatchers.IO){
                    val request:Request=Request.Builder()
                        .addHeader("cookie", getToken())
                        .addHeader("gid",gid.toString())
                        .addHeader("wishPrice",wishPrice.toString())
                        .url("http://${AAAhomeApplication.context.getString(R.string.basePort)}/changeWishById")
                        .build()
                    val response=OkHttpClient().newCall(request).execute()
                    if (response.header("status").equals("timeout")) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                AAAhomeApplication.context, "timeout", Toast.LENGTH_SHORT
                            ).show()
                            //将过期token去除
                            val edit =
                                AAAhomeApplication.context.getSharedPreferences("token", 0).edit()
                            edit.putString("token", "")
                            edit.apply()
                            AAAhomeApplication.startActivity<LoginActivity>()
                            false
                        }
                    }
                }
            }
        }
        fun hideKeyboard(context: Activity){
            val imm:InputMethodManager= context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(context.window.decorView.windowToken,0)
        }

    }
}