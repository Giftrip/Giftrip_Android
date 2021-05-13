package com.flash21.giftrip_android.viewmodel_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flash21.giftrip_android.viewmodel.NotificationFragmentViewModel

class NotificationFragmentViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(NotificationFragmentViewModel::class.java)){
            return NotificationFragmentViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}