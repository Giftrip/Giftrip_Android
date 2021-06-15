package com.flash21.giftrip_android.model.coursesList

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CourseListService {
    @GET("/course/getCourses?page=1&size=1")
    suspend fun gerCourseList(
        @Header("Authorization") type:String
    ):Response<CourseList>
}