package com.flash21.giftrip_android.model.bottomSheet

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.Log
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.flash21.giftrip_android.R

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
    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val view = getView(viewHolder)

            getDefaultUIUtil().onDraw(
                c,
                recyclerView,
                view,
                dX,
                dY,
                actionState,
                isCurrentlyActive
            )
        }
    }

    private fun getView(viewHolder: RecyclerView.ViewHolder): View {
        return (viewHolder as BottomSheetAdapter.Holder).itemView.findViewById(R.id.item_layout)
    }
}