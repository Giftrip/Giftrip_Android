package com.flash21.giftrip_android.network

import com.flash21.giftrip_android.model.nfc_data.NfcResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NfcAPI {
    @GET("/spot/getQuizByNfc/0")
    fun getQuizByNfc(@Query("nfcCode") nfcCode: String?): Call<NfcResponse>
}