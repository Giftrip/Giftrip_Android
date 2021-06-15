package com.flash21.giftrip_android.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private var instance: Retrofit? = null

    val retrofitBuild : Retrofit = Retrofit.Builder()
        .baseUrl("http://210.114.22.183:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val postAuth : PostAuthAPI = retrofitBuild.create(PostAuthAPI::class.java)

    companion object {
        val instance = RetrofitClient()
    }
}