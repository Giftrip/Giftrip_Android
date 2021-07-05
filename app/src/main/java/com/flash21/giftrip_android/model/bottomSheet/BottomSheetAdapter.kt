package com.flash21.giftrip_android.model.bottomSheet

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.model.nfc_data.SpotResponse
import com.flash21.giftrip_android.model.sharedPreference.MyApplication
import com.flash21.giftrip_android.model.spotList.SpotContent
import com.flash21.giftrip_android.model.spotList.SpotList
import com.flash21.giftrip_android.network.RetrofitClient
import com.flash21.giftrip_android.view.SpotActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class BottomSheetAdapter(private val context: Context) :
    RecyclerView.Adapter<BottomSheetAdapter.Holder>() {
    val spotList = ArrayList<SpotContent>()
    override fun getItemCount(): Int {
        return spotList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.bottom_sheet_recyclerview_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.bind(spotList[position], position)

        holder.itemView.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                val call: Call<SpotResponse> = RetrofitClient.instance.nfcAPI.getSpot(
                    "Bearer ${MyApplication.prefs.getString("AccessToken", "null")}",
                    position
                )

                call.enqueue(object : retrofit2.Callback<SpotResponse>{
                    override fun onResponse(
                        call: Call<SpotResponse>,
                        response: Response<SpotResponse>
                    ) {
                        when(response.code()){
                            200->{
                                val intent = Intent(context, SpotActivity::class.java)
                                intent.putExtra("address", response.body()!!.address)
                                intent.putExtra("description", response.body()!!.description)
                                intent.putExtra("lon", response.body()!!.lon)
                                intent.putExtra("completed", response.body()!!.completed)
                                intent.putExtra("courseIdx", response.body()!!.courseIdx)
                                intent.putExtra("thumbnails", response.body()!!.thumbnails)
                                intent.putExtra("title", response.body()!!.title)
                                intent.putExtra("idx", response.body()!!.idx)
                                intent.putExtra("lat", response.body()!!.lat)
                                context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                            }
                        }
                    }

                    override fun onFailure(call: Call<SpotResponse>, t: Throwable) {

                    }
                })
            }
        }

    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        private val spotTitle = itemView?.findViewById<TextView>(R.id.item_title)
        private val spotAddress = itemView?.findViewById<TextView>(R.id.item_address)
        private val spotImage = itemView!!.findViewById<ImageView>(R.id.item_image)
        private val spotDescription = itemView?.findViewById<TextView>(R.id.item_description)

        fun bind(courseList: SpotContent, position: Int) {
            Log.d("LOG", "${courseList.thumbnails}")
            spotTitle!!.text = courseList.title
            spotAddress!!.text = courseList.address
            spotDescription!!.text = courseList.description
            Glide.with(context)
                .load(courseList.thumbnails.last())
                .transform(CenterCrop(),RoundedCorners(30))
                .override(300,300)
                .into(spotImage)

        }

    }

    fun addItem(item: SpotList) {
        spotList.addAll(item.content)
        notifyDataSetChanged()
    }
}