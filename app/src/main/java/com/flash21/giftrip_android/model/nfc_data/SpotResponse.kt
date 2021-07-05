package com.flash21.giftrip_android.model.nfc_data

data class SpotResponse(
    val address: String,
    val description: String,
    val lon: Int,
    val completed: Boolean,
    val courseIdx: Int,
    val thumbnails: ArrayList<String>,
    val title: String,
    val idx: Int,
    val lat: Int
)