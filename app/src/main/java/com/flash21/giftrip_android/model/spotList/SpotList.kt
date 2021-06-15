package com.flash21.giftrip_android.model.spotList

data class SpotList(
    val completed: Int,
    val content: List<SpotContent>,
    val totalElements: Int,
    val totalPage: Int
)