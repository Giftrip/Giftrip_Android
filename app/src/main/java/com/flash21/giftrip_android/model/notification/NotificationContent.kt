package com.flash21.giftrip_android.model.notification

data class NotificationContent(
    val content: String,
    val createdAt: String,
    val idx: Int,
    val thumbnail: String,
    val title: String,
    val viewed: Boolean
)