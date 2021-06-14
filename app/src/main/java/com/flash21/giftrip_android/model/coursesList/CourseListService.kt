package com.flash21.giftrip_android.model.coursesList

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface CourseListService {
    @GET("/course/getCourses")
    fun gerCourseList(
        @Header("Authorization") type:String
    ):retrofit2.Call<Content>

}