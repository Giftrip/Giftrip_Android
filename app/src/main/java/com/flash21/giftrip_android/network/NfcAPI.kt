package com.flash21.giftrip_android.network

import com.flash21.giftrip_android.model.nfc_data.NfcResponse
import com.flash21.giftrip_android.model.nfc_data.SpotResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface NfcAPI {
    @GET("/spot/getQuizByNfc/{idx}")
    fun getQuizByNfc(
        @Header("Authorization") type: String,
        @Path("idx") id: Int,
        @Query(
            "nfcCode"
        ) nfcCode: String?
    ): Call<NfcResponse>

    @GET("/spot/getSpot/{idx}")
    fun getSpot(
        @Header("Authorization")  type: String,
        @Path("idx") id: Int
    ): Call<SpotResponse>

}