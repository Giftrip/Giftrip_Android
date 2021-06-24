package com.flash21.giftrip_android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flash21.giftrip_android.model.spotList.SpotList
import com.flash21.giftrip_android.model.spotList.SpotListService
import com.flash21.giftrip_android.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class HomeFragmentViewModel : ViewModel() {
    private lateinit var retrofit: Retrofit
    private lateinit var courseListService: SpotListService
    var data = MutableLiveData<SpotList>()
    private val token: String =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJpZHgiOjEsImF1dGhUeXBlIjoiQUNDRVNTIiwiZXhwIjoxNjI0NDk5NzcxfQ.2R_JpWXpM54XB7yi4OLlt80zNtzKrW5EQrwf3_I7KahrvVTHotj-nUFW_1LXE5SL"

    fun getSpotList() {
        retrofit = RetrofitClient.instance.retrofitBuild
        courseListService = RetrofitClient.instance.courseList
        CoroutineScope(Dispatchers.IO).launch {
            val response = courseListService.gerCourseList("Bearer $token")
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    data.value = response.body()
                    Log.d("Response", "Response : ${data.value}")
                }
            } else {
                Log.d("ResponseError", "ResponseError : ${response.code()}")
            }
        }
    }

}