package com.flash21.giftrip_android.model.notification.network

import com.flash21.giftrip_android.model.notification.NotificationList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface NotificationService {
    @GET("/notice/getNotices?page=1&size=20")
    suspend fun gerNotificationList(
        @Header("Authorization") type:String
    ): Response<NotificationList>
}