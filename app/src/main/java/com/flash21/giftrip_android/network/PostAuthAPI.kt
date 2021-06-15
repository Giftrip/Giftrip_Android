package com.flash21.giftrip_android.network

import com.flash21.giftrip_android.model.logindata.LoginRequest
import com.flash21.giftrip_android.model.logindata.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PostAuthAPI {
    @POST("auth/login")
    fun login(@Body loginResquest: LoginRequest):Call<LoginResponse>
}