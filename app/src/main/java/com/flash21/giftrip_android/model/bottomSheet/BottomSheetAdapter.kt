package com.flash21.giftrip_android.model.bottomSheet

import android.content.Context
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
import com.flash21.giftrip_android.model.spotList.SpotContent
import com.flash21.giftrip_android.model.spotList.SpotList

class BottomSheetAdapter(private val context: Context) :
    RecyclerView.Adapter<BottomSheetAdapter.Holder>() {
    private val spotList = ArrayList<SpotContent>()
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
                .override(350,350)
                .into(spotImage)
        }

    }

    fun addItem(item: SpotList) {
        spotList.addAll(item.content)
        notifyDataSetChanged()
    }
}