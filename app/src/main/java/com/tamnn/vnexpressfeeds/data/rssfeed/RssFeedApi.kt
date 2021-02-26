package com.tamnn.vnexpressfeeds.data.rssfeed

import com.tamnn.vnexpressfeeds.data.model.RssFeed
import retrofit2.Call
import retrofit2.http.GET

interface RssFeedApi {
    @GET("rss/tin-moi-nhat.rss")
    fun getRssFeed(): Call<RssFeed>
}