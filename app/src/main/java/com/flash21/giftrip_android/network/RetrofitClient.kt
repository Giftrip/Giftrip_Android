package com.flash21.giftrip_android.network

import com.flash21.giftrip_android.model.spotList.SpotListService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient {
    private var instance: Retrofit? = null

    val retrofitBuild : Retrofit = Retrofit.Builder()
        .baseUrl("http://210.114.22.183:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val postAuth : PostAuthAPI = retrofitBuild.create(PostAuthAPI::class.java)
    val nfcAPI: NfcAPI = retrofitBuild.create(NfcAPI::class.java)
    val courseList : SpotListService = retrofitBuild.create(SpotListService::class.java)

    companion object {
        val instance = RetrofitClient()
    }
}