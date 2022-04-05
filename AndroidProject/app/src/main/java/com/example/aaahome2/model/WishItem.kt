package com.example.aaahome2.model

data class WishItem(
    var id: Int,
    var gameName: String,
    var priceNow: Int,
    var priceLowest: Int,
    var coverimageUrl: String,
    var detailPage: String,
    var relaseDate: String,
    //这个时间戳会在愿望单中被改为期望价格
    var nearlyLowestTimestamp: Int,
    var appraise:String,
    var imgSrc:String,
    //mark1是原价
    var mark1: String,
    var mark2: String
)