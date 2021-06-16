package com.flash21.giftrip_android.network

import com.flash21.giftrip_android.model.logindata.LoginRequest
import com.flash21.giftrip_android.model.logindata.LoginResponse
import com.flash21.giftrip_android.model.phoneNumberData.PhoneNumberResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PostAuthAPI {
    @POST("auth/login")
    fun login(@Body loginResquest: LoginRequest):Call<LoginResponse>

    @POST("auth/createAuthCode")
    fun getConfirmNumber(@Query("phoneNumber") phoneNumber : String):Call<PhoneNumberResponse>
}