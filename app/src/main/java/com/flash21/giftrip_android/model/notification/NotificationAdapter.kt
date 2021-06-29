package com.flash21.giftrip_android.model.notification

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
import com.flash21.giftrip_android.model.bottomSheet.BottomSheetAdapter
import com.flash21.giftrip_android.model.spotList.SpotContent
import com.flash21.giftrip_android.model.spotList.SpotList

class NotificationAdapter(private val context: Context) :
    RecyclerView.Adapter<NotificationAdapter.Holder>() {
    val notificationList = ArrayList<NotificationContent>()
    override fun getItemCount(): Int {
        return notificationList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationAdapter.Holder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.notification_recyclerview_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: NotificationAdapter.Holder, position: Int) {

        holder.bind(notificationList[position], position)
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        private val notificationTitle = itemView?.findViewById<TextView>(R.id.notification_title)
        private val notificationDescription = itemView?.findViewById<TextView>(R.id.notification_description)


        fun bind(notificationList: NotificationContent, position: Int) {
            Log.d("LOG", notificationList.thumbnail)
            notificationTitle!!.text = notificationList.title
            notificationDescription!!.text = notificationList.content
            

        }

    }

    fun addItem(item: NotificationList) {
        notificationList.addAll(item.content)
        notifyDataSetChanged()
    }
}