package com.example.aaahome2.ui.login

import android.annotation.SuppressLint
import android.app.Service
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.aaahome2.AAAhomeApplication
import com.example.aaahome2.MainActivity
import com.example.aaahome2.R
import com.example.aaahome2.Tools
import com.example.aaahome2.Tools.Companion.getJson
import com.example.aaahome2.model.RequestLogin
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        val edit =
            AAAhomeApplication.context.getSharedPreferences("token", 0)
        val token = edit.getString("token", "")
        if (!token.equals("")) {
            AAAhomeApplication.startActivity<MainActivity>()
        }
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_login)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val textView = findViewById<TextView>(R.id.textView)
        val login = findViewById<Button>(R.id.login)
        val register = findViewById<Button>(R.id.register)
        val email = findViewById<TextView>(R.id.email)
        val pwd = findViewById<TextView>(R.id.pwd)

        login.setOnClickListener {
            //todo 检查输入是否合法
            val login = RequestLogin(email.text.toString(), pwd.text.toString(), "")
            val gson = Gson()
            val json: String = gson.toJson(login)
            val request = Request.Builder()
                .addHeader("requestLogin", json)
                .url("http://${resources.getString(R.string.basePort)}/login")
                .build()
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    val response = OkHttpClient().newCall(request).execute()
                    if (response.header("status") == "-1") {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                AAAhomeApplication.context,
                                "用户不存在或密码错误！",
                                Toast.LENGTH_SHORT
                            ).show()
                            pwd.text = ""
                        }
                    }
                    if (response.header("status") == "1") {
                        val edit =
                            AAAhomeApplication.context.getSharedPreferences("token", 0).edit()
                        val info = response.header("Set-Cookie")!!.substring(6)
                        Log.d("token", "$info")
                        edit.putString("token", info)
                        edit.putString("email",email.text.toString())
                        edit.apply()
                        AAAhomeApplication.startActivity<MainActivity>()
                    }
                }
            }
        }
        register.setOnClickListener {
            Tools.changeGame(2,100)
        }

    }

    fun saveWish(map: Map<Int, Int>) {

    }
}