package com.flash21.giftrip_android.model.bottomSheet

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

import com.flash21.giftrip_android.view.SwipeCallBack
import com.google.android.gms.maps.OnMapReadyCallback
import java.security.Provider

class TouchHelperCallBack(Swipe : SwipeCallBack, bottomSheetAdapter: BottomSheetAdapter) : ItemTouchHelper.Callback() {
    private val swipeCallBack = Swipe
    private val bottomSheetAdapter = bottomSheetAdapter
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(0, ItemTouchHelper.LEFT or 0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }
    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {

        return defaultValue * 100
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        swipeCallBack.swipeMap(viewHolder.adapterPosition, bottomSheetAdapter)
        return 200f
    }
}