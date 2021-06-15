package com.flash21.giftrip_android.model.logindata

data class LoginResponse(
    val accessToken: AccessToken?,
    val refreshToken: RefreshToken?,
    val message : String?
)