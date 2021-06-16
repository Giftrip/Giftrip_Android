package com.flash21.giftrip_android.model.spotList

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface SpotListService {
    @GET("/spot/getSpots?page=1&size=1&courseIdx=1")
    suspend fun gerCourseList(
        @Header("Authorization") type:String
    ):Response<SpotList>
}