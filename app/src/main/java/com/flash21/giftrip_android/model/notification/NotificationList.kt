package com.flash21.giftrip_android.model.notification

data class NotificationList(
    val content: List<NotificationContent>,
    val totalElements: Int,
    val totalPage: Int
)