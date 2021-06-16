package com.flash21.giftrip_android.model.registerData

data class RegisterResponse(
    val accessToken: AccessToken?,
    val refreshToken: RefreshToken?,
    val message : String?
)