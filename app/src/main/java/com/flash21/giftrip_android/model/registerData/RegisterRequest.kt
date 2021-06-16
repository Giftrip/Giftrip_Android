package com.flash21.giftrip_android.model.registerData

data class RegisterRequest(
    val code : String,
    val phoneNumber : String,
    val pw : String,
    val name : String,
    val birth : String
)
