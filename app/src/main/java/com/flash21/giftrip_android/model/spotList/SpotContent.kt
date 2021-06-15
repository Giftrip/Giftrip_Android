package com.flash21.giftrip_android.model.spotList

data class SpotContent(
    val address: String,
    val completed: Boolean,
    val courseIdx: Int,
    val description: String,
    val idx: Int,
    val lat: Double,
    val lon: Double,
    val thumbnails: List<String>,
    val title: String
)