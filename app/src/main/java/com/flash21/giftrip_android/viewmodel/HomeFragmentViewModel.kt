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
import retrofit2.Retrofit

class HomeFragmentViewModel : ViewModel() {
    private lateinit var retrofit: Retrofit
    private lateinit var courseListService : SpotListService
    var data = MutableLiveData<SpotList>()
    private val token : String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJpZHgiOjEsImF1dGhUeXBlIjoiQUNDRVNTIiwiZXhwIjoxNjIzNzQzMzkyfQ.0ear5ZCbCWalR4tleX0CusxkklLKBh8kQf8JCJ3l4Gg4cltYWIJhm9S1HgXrhnj7"
    fun getSpotList(){
        retrofit = RetrofitClient.instance.retrofitBuild
        courseListService = RetrofitClient.instance.courseList
        CoroutineScope(Dispatchers.IO).launch {
            val response = courseListService.gerCourseList("Bearer $token")
            if (response.isSuccessful){
                response.body().let {
                    Log.d("Response","Response : ${response.body()}")
                //    data.value = response.body()
                }
            }else{
                Log.d("ResponseError","ResponseError : ${response.code()}")
            }
        }
    }

}