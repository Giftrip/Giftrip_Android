package com.flash21.giftrip_android.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.flash21.giftrip_android.model.coursesList.CourseListService
import com.flash21.giftrip_android.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class HomeFragmentViewModel : ViewModel() {
    private lateinit var retrofit: Retrofit
    private lateinit var courseListService : CourseListService
    private val token : String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJpZHgiOjEsImF1dGhUeXBlIjoiQUNDRVNTIiwiZXhwIjoxNjIzNzI3Mjc3fQ.YiU-8OKgzoDEhw3_ICOeF2k63Yiai676ypiRPm69EWEb6O2KDXe9Eku1Sv1oKIzA"
    fun getCourseList(){
        retrofit = RetrofitClient.instance.retrofitBuild
        courseListService = RetrofitClient.instance.courseList
        CoroutineScope(Dispatchers.IO).launch {
            val response = courseListService.gerCourseList("Bearer $token")
            if (response.isSuccessful){
                response.body().let {
                    Log.d("Response","Response : ${response.body()}")
                }
            }else{
                Log.d("ResponseError","ResponseError : ${response.code()}")
            }
        }


    }
}