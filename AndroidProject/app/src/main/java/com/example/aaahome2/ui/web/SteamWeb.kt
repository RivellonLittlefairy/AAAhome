package com.example.aaahome2.ui.web

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.aaahome2.R
import com.example.aaahome2.databinding.ActivitySteamwebBinding

class SteamWeb : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steamweb)
        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled=true
        webView.webViewClient= WebViewClient()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;
        }
        //todo
        //这里拿不到url参数
        intent.getStringExtra("url")?.let {
            Log.d("mes", "https://$it")
            webView.loadUrl(it)}
    }
}