package com.flash21.giftrip_android.model

data class LoginResponse(
    val accessToken: AccessToken?,
    val refreshToken: RefreshToken?,
    val message : String?
)