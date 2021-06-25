package com.flash21.giftrip_android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flash21.giftrip_android.model.notification.NotificationList
import com.flash21.giftrip_android.model.notification.network.NotificationService
import com.flash21.giftrip_android.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class NotificationFragmentViewModel : ViewModel(){
    private lateinit var retrofit: Retrofit
    private lateinit var notificationListService: NotificationService
    var data = MutableLiveData<NotificationList>()

    fun getNotificationList(accessToken: String) {
        retrofit = RetrofitClient.instance.retrofitBuild
        notificationListService = RetrofitClient.instance.notificationList
        CoroutineScope(Dispatchers.IO).launch {
            val response = notificationListService.gerNotificationList("Bearer $accessToken")
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