package com.flash21.giftrip_android.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flash21.giftrip_android.event.Event
import com.flash21.giftrip_android.model.nfc_data.NfcResponse
import com.flash21.giftrip_android.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    private var payloadStr = MutableLiveData<String>()
    val payloadStrData: LiveData<String>
        get() = payloadStr

    private var _toasts = MutableLiveData<Event<String>>()
    val toasts: LiveData<Event<String>>
        get() = _toasts

    fun getPayloadStr(payLoad: String){
        payloadStr.value = payLoad
    }

    fun getQuiz(payLoad: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call: Call<NfcResponse> = RetrofitClient.instance.nfcAPI.getQuizByNfc(
                "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJpZHgiOjEsImF1dGhUeXBlIjoiQUNDRVNTIiwiZXhwIjoxNjI0NTAzMjg5fQ.DA6yzjqdcAzJ3_DtknNesSA9PsiRpCrTgID9HgksoMOpobzBmLygJfDAzI-Fk2dJ",
                1,
                nfcCode = payLoad
            )

            call.enqueue(object : retrofit2.Callback<NfcResponse>{
                override fun onResponse(call: Call<NfcResponse>, response: Response<NfcResponse>) {
                    Log.d("serverLog", "code: ${response.code()}")
                    when(response.code()){
                        200->{
                            Log.d("serverLog", "youtube: ${response.body()?.youtube}")
                            Log.d("serverLog", "quiz: ${response.body()?.quiz}")
                            _toasts.value = Event("youtube: ${response.body()?.youtube}")
                        }
                        401->{
                            Log.d("serverLog", "인증 안됨")
                        }
                        400->{
                            Log.d("serverLog", "error, code: ${response.code()}")
                            Log.d("serverLog", "error, code: ${response.message()}")
                        }
                        404->{
                            Log.d("serverLog", "404")
                        }
                        409->{
                            Log.d("serverLog", "409")
                        }
                        410->{
                            Log.d("serverLog", "토큰 만료")
                        }
                    }
                }

                override fun onFailure(call: Call<NfcResponse>, t: Throwable) {
                    Log.d("serverLog", "통신 실패")
                    Log.d("serverLog", "error")
                }
            })
        }
    }

}