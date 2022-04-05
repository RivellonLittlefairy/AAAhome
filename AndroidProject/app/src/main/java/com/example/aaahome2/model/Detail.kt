package com.example.aaahome2.model

data class Detail(
    val id:Int,
    val gameName:String,
    val url:String,
    val price:Int,
    val highPrice:Int,
    val isDlc:Boolean,
    val appraise:String,
    val comments:String,
    val dlcNames:List<String>,
    val HDCoverImg:String,
    val tag:List<String>,
    val HDImg:List<String>,
    val HDMovie:List<String>,
    val mark1:String,
    val mark2:String
)