package com.flash21.giftrip_android.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.flash21.giftrip_android.R
import com.flash21.giftrip_android.model.coursesList.Content
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BottomSheetAdapter(private val context: Context, private val SpotList: List<Content>) :
    RecyclerView.Adapter<BottomSheetAdapter.Holder>() {

    override fun getItemCount(): Int {
        return SpotList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_recyclerview_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

//        holder.bind(SpotList[position], position)
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        private val spotTitle = itemView?.findViewById<TextView>(R.id.item_title)
        private val spotAddress = itemView?.findViewById<ImageView>(R.id.item_address)
        private val spotImage = itemView?.findViewById<TextView>(R.id.item_image)
        private val spotDescription = itemView?.findViewById<TextView>(R.id.item_description)
        private val layout = itemView?.findViewById<LinearLayout>(R.id.item_layout)

//        fun bind(notepad: NotePadEntity, position: Int) {

//        }


    }
}