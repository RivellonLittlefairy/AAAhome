package com.example.aaahome2

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Build

class AAAhomeApplication() : Application() {
    //静态变量在伴生类中创建即可
    companion object {
        var mes=""
        //这个注释的含义是，告诉编译器这个变量没有内存泄露的风险
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        inline fun <reified T> startActivity() {
            val intent = Intent(context, T::class.java)
            if ((Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent)
        }

        inline fun <reified T> startActivity(block: (intent: Intent) -> Unit) {
            val intent = Intent(context, T::class.java)
            if ((Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            block(intent)
            context.startActivity(intent)
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}